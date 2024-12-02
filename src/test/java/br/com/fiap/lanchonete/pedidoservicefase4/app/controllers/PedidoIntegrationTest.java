package br.com.fiap.lanchonete.pedidoservicefase4.app.controllers;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;



@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class PedidoIntegrationTest {
    
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Autowired
    private MockMvc mockMvc;



    @Test
    public void shouldNotReturnPedidoWhenGetPedidoById() {
        try {
            mockMvc.perform(MockMvcRequestBuilders.get("/pedidos/{id}", 1))
                    .andReturn();
            fail("Expected RuntimeException was not thrown");
        } catch (RuntimeException e) {
            assertEquals("Pedido não encontrado", e.getMessage());
        } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
    }


    @Test
    public void shouldReturnNotFoundWhenAddItemsToNonExistingPedido() {
        try {
            mockMvc.perform(MockMvcRequestBuilders.put("/pedidos/{id}/add-items", 999)
                            .contentType("application/json")
                            .content("[{\"id\":1,\"name\":\"Item1\",\"price\":10.0}]"))
                    .andReturn();
            fail("Expected RuntimeException was not thrown");
        } catch (RuntimeException e) {
            assertEquals("Pedido não encontrado", e.getMessage());
        } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
    }


    @Test
    public void shouldReturnNotFoundWhenDeleteItemFromNonExistingPedido() {
        try {
            mockMvc.perform(MockMvcRequestBuilders.delete("/pedidos/{id}/delete-item/{idItem}", 999, 1))
                    .andReturn();
            fail("Expected RuntimeException was not thrown");
        } catch (RuntimeException e) {
            assertEquals("Pedido não encontrado", e.getMessage());
        } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
    }


    @Test
    public void shouldReturnNotFoundWhenCheckoutFails() {
        try {
            mockMvc.perform(MockMvcRequestBuilders.patch("/pedidos/{id}/checkout", 999)
                            .param("collector", "1")
                            .param("pos", "pos"))
                    .andReturn();
            fail("Expected RuntimeException was not thrown");
        } catch (RuntimeException e) {
            assertEquals("Pedido não encontrado", e.getMessage());
        } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
    }




}
