package one.digitalinnovation.personapi.controllers;

import one.digitalinnovation.personapi.model.Computer;
import one.digitalinnovation.personapi.service.ComputerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api/v1/computer")
public class ComputerController {
    private final ComputerService computerService;

    @Autowired
    public ComputerController(ComputerService computerService) {
        this.computerService = computerService;
    }

    @GetMapping("/list")
    public Stream<?> getAll() {
        return computerService.getAll();
    }

    @GetMapping("/searchByHostname/{hostname}")
    public List<Computer> searchComputerByHostname(@PathVariable("hostname") String hostname) {
        return computerService.searchComputerByHostname(hostname);
    }

    @GetMapping("/findByHostname/{hostname}")
    public Computer findComputerByHostname(@PathVariable("hostname") String hostname) {
        return computerService.findComputerByHostname(hostname);
    }

    @PostMapping("")
    public Computer createComputer(@RequestBody Computer Computer) {
        return computerService.createComputer(Computer);
    }

    @PutMapping("")
    public Computer updateComputer(@RequestBody Computer Computer) {
        return computerService.updateComputer(Computer);
    }

//    @PutMapping("/move/{hostname}")
//    public ResponseMessageDTO updateComputer(@PathVariable("hostname") String hostname, @RequestBody Computer Computer)
//            throws Exception, NotFoundObjectException {
//        return computerService.moveComputer(hostname, Computer);
//    }

//    @PutMapping("/disable")
//    public ResponseMessageDTO disableComputer(@RequestBody Computer Computer)
//            throws Exception, NotFoundObjectException {
//        return computerService.disableComputer(Computer);
//    }

    @DeleteMapping("")
    public void deleteComputer(@RequestBody Computer Computer) {
        computerService.deleteComputer(Computer);
    }

//    @PutMapping("/updateParam/{hostname}")
//    public ResponseMessageDTO alterParameter(@PathVariable("hostname") String hostname, @PathParam("param") String param, @PathParam("value") String value) {
//
//        return computerService.alterParameter(hostname, param, value);
//    }

//    @PutMapping("/updateParamByUrl/{hostname}")
//    public ResponseMessageDTO updateParamByUrl(@PathVariable("hostname") String hostname, @RequestParam Map<?, ?> map) {
//        return computerService.alterParameterByBody(hostname, map);
//    }

//    @PostMapping("/updateParamByBody/{hostname}")
//    public ResponseMessageDTO updateParamByBody(@PathVariable("hostname") String hostname, @RequestBody Map<?, ?> map) {
//        return computerService.alterParameterByBody(hostname, map);
//    }

//    @DeleteMapping("/deleteParam/{hostname}")
//    public ResponseMessageDTO deleteParameter(@PathVariable("hostname") String hostname, @PathParam("param") String param) {
//
//        return computerService.deleteParameter(hostname, param);
//    }

//    @ResponseStatus(HttpStatus.FORBIDDEN)
//    @ExceptionHandler(UserNotAuthenticated.class)
//    public String handleValidationExceptions(UserNotAuthenticated ex) {
//        return ex.getMessage();
//    }
//
//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    @ExceptionHandler(NotFoundObjectException.class)
//    public String handleValidationExceptions(NotFoundObjectException ex) {
//        return ex.getMessage();
//    }

}
