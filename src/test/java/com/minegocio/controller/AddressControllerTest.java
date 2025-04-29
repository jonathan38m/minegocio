package com.minegocio.controller;

import com.minegocio.model.dto.AddressDTO;
import com.minegocio.service.AddressService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(AddressController.class)
public class AddressControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AddressService addressService;

    @Test
    void shouldReturnAddresses_WhenCustomerHasAddresses() throws Exception {
        AddressDTO dto = new AddressDTO();
        dto.setId(1L);
        dto.setAddress("123 Main St");
        dto.setMain(true);

        when(addressService.getAddressesByCustomerId(1L))
                .thenReturn(Collections.singletonList(dto));

        mockMvc.perform(get("/api/1/addresses"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].address").value("123 Main St"))
                .andExpect(jsonPath("$[0].main").value(true));
    }

    @Test
    void shouldReturn404_WhenCustomerHasNoAddresses() throws Exception {
        when(addressService.getAddressesByCustomerId(999L))
                .thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/999/addresses"))
                .andExpect(status().isNotFound());
    }
}
