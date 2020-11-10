package pl.com.mazniak.swietapolska;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Controller
public class HolidaysController  {
    HolidaysDTOManager holidaysDTOManager;
    RestTemplate restTemplate;

    @Autowired
    public HolidaysController(HolidaysDTOManager holidaysDTOManager) {
        this.holidaysDTOManager = holidaysDTOManager;
        getDataHolidays(2019);
    }


    public void getDataHolidays(int year) {
        restTemplate = new RestTemplate();
        Holiday[] holidaysArray = restTemplate.getForObject(
                "https://date.nager.at/api/v2/publicholidays/"+ year
                        +"/PL", Holiday[].class);
        holidaysDTOManager.setHolidayDTOList(Arrays.asList(holidaysArray));
    }
}
