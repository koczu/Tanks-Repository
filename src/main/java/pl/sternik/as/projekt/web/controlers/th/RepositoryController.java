package pl.sternik.as.projekt.web.controlers.th;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.sternik.as.projekt.entities.Tank;
import pl.sternik.as.projekt.services.NotificationService;
import pl.sternik.as.projekt.services.RepositoryService;

import java.util.List;


@Controller
public class RepositoryController {

    @Autowired
    @Qualifier("spring-data")
    private RepositoryService repositoryService;

    @Autowired
    private NotificationService notificationService;


    @ModelAttribute("tanksAll")
    public List<Tank> populateTanks() {
        return this.repositoryService.findAll();
    }

    @ModelAttribute("tanksInMaintenance")
    public List<Tank> populateTanksInMaintenance() {
        return this.repositoryService.findAllInMaintenance();
    }

    @ModelAttribute("tanksDuplicates")
    public List<Tank> populateTanksDuplicates() {
        return this.repositoryService.findAllDuplicates();
    }


    @RequestMapping({"/", "/index"})
    public String index(Model model) {
        return "th/index";
    }

    @RequestMapping(value = "/tanks", method = RequestMethod.GET)
    public String showMainPage(Model model) {
        model.addAttribute("MyMessages", notificationService.getNotificationMessages());
        return "th/repository";
    }

    @RequestMapping("/inmaintenance")
    public String showInMaintenance() {
        return "th/inmaintenance";
    }


    @RequestMapping("/duplicates")
    public String showDuplicatesPage() {
        return "th/duplicates";
    }
}