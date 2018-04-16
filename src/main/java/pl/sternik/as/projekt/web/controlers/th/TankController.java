package pl.sternik.as.projekt.web.controlers.th;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.sternik.as.projekt.entities.Status;
import pl.sternik.as.projekt.entities.Tank;
import pl.sternik.as.projekt.entities.Type;
import pl.sternik.as.projekt.services.NotificationService;
import pl.sternik.as.projekt.services.NotificationService.NotificationMessage;
import pl.sternik.as.projekt.services.RepositoryService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Controller
public class TankController {

    @Autowired
    private Logger logger;

    @Autowired
    @Qualifier("spring-data")
    private RepositoryService repositoryService;

    @Autowired
    private NotificationService notifyService;

    @ModelAttribute("statusAll")
    public List<Status> populateStatus() {
        return Arrays.asList(Status.ALL);
    }

    @ModelAttribute("typesAll")
    public List<Type> populateType() {
        return Arrays.asList(Type.ALL);
    }

    @ModelAttribute("MyMessages")
    public List<NotificationMessage> populateMessages() {
        logger.info("Make message!");
        return notifyService.getNotificationMessages();
    }


    @GetMapping(value = "/tanks/{id}")
    public String view(@PathVariable("id") Long id, final ModelMap model) {
        Optional<Tank> result;
        result = repositoryService.findById(id);
        if (result.isPresent()) {
            Tank tank = result.get();
            model.addAttribute("tank", tank);
            return "th/tanks";
        } else {
            notifyService.addErrorMessage("Cannot find tank #" + id);
            model.clear();
            return "redirect:/tanks";
        }
    }

    @RequestMapping(value = "/tanks/{id}/json", produces = "application/json", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Tank> viewAsJson(@PathVariable("id") Long id, final ModelMap model) {
        Optional<Tank> result;
        result = repositoryService.findById(id);
        if (result.isPresent()) {
            Tank tank = result.get();
            return new ResponseEntity<Tank>(tank, HttpStatus.OK);
        } else {
            notifyService.addErrorMessage("Cannot find tank #" + id);
            model.clear();
            return new ResponseEntity<Tank>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/tanks", params = {"save"}, method = RequestMethod.POST)
    public String saveTank(@Valid Tank tank, BindingResult bindingResult, ModelMap model) {
        // @Valid
        if (bindingResult.hasErrors()) {
            notifyService.addErrorMessage("Please fill the form correctly!");
            model.addAttribute("MyMessages", notifyService.getNotificationMessages());
            return "th/tanks";
        }


        Optional<Tank> result = repositoryService.edit(tank);
        if (result.isPresent())
            notifyService.addInfoMessage("Save was succeful");
        else
            notifyService.addErrorMessage("Save failed");
        model.clear();
        return "redirect:/tanks";
    }

    @RequestMapping(value = "/tanks", params = {"create"}, method = RequestMethod.POST)
    public String createTank(@Valid Tank tank, BindingResult bindingResult, ModelMap model) {
        if (bindingResult.hasErrors()) {
            notifyService.addErrorMessage("Please fill the form correctly!");
            model.addAttribute("MyMessages", notifyService.getNotificationMessages());
            return "th/tanks";
        }
        repositoryService.create(tank);
        model.clear();
        notifyService.addInfoMessage("Saved new tank");
        return "redirect:/tanks";
    }

    @RequestMapping(value = "/tanks", params = {"remove"}, method = RequestMethod.POST)
    public String removeRow(final Tank tank, final BindingResult bindingResult, final HttpServletRequest req, @RequestParam Integer remove) {
        Optional<Boolean> result = repositoryService.deleteById(remove.longValue());
        return "redirect:/tanks";
    }

    @RequestMapping(value = "/tanks/create", method = RequestMethod.GET)
    public String showMainPages(final Tank tank) {
        tank.setDateOfRelease(Calendar.getInstance().getTime());
        return "th/tanks";
    }
}