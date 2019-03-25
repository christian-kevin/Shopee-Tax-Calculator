package com.kevin.app.controller.JSPPageController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TaxCalculatorController {

    @GetMapping(value="/tax-calculator")
    public String renderTaxCalculator(){
        return "tax-calculator";
    }
}
