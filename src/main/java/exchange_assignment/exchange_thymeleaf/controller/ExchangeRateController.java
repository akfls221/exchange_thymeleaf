package exchange_assignment.exchange_thymeleaf.controller;

import exchange_assignment.exchange_thymeleaf.CountryEnum;
import exchange_assignment.exchange_thymeleaf.controller.form.ExchangeRequestForm;
import exchange_assignment.exchange_thymeleaf.domain.Exchange;
import exchange_assignment.exchange_thymeleaf.service.ExchangeRateService;
import lombok.RequiredArgsConstructor;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
public class ExchangeRateController {

    private final ExchangeRateService service;

    @GetMapping("/exchange")
    public String exchangeRate(Model model) {

        model.addAttribute("exchange", new Exchange());
        model.addAttribute("countries", CountryEnum.values());

        return "exchangeForm";
    }

    @PostMapping("/exchange")
    public String getExchangeResult(@Validated @ModelAttribute("exchange") ExchangeRequestForm requestDto, BindingResult bindingResult, Model model) throws java.text.ParseException {

        if(bindingResult.hasErrors()){
            return "exchangeForm";
        }

        String receivable = service.getExchangeResult(requestDto);

        Exchange result = Exchange.builder()
                .country(requestDto.getCountry())
                .exchangeRate(requestDto.getExchangeRate())
                .sendPrice(requestDto.getSendPrice())
                .receivable(receivable)
                .build();

        model.addAttribute("result", result);
        model.addAttribute("countries", CountryEnum.values());
        return "exchangeResultForm";
    }

    @PostMapping("/getExchangeInfo/{country}")
    @ResponseBody
    public Exchange getExchangeInfo(@PathVariable String country) {
        return service.getExchangeInfo(country);
    }
}
