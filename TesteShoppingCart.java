/**
* Engenharia de Software Moderna - Testes  (Cap. 8)
* Prof. Marco Tulio Valente
* 
* Exercício simples de teste de unidade (ShoppingCart)
*
*/

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;

public class TesteShoppingCart {

  private ShoppingCart shoppingCart;

  @Before
  public void setUp() {
    shoppingCart = new ShoppingCart();
    shoppingCart.addItem(new Item("ESM", 65.0));
    shoppingCart.addItem(new Item("GoF", 120.0));
  }
  @Test
  public void testAddItem() {
    // Testa se um item é adicionado corretamente ao carrinho
    Item newItem = new Item("Clean Code", 80.0);
    shoppingCart.addItem(newItem);
    
    // Verifica se o número de itens aumentou
    assertEquals(3, shoppingCart.getItemCount());
    
    // Verifica se o item foi adicionado à lista
    assertTrue(shoppingCart.getItems().contains(newItem));
  }
  @Test
  public void testRemoveItem() {
    // Testa se um item é removido corretamente do carrinho
    Item itemToRemove = new Item("ESM", 65.0);
    
    // Remove o item (note que precisamos de um item igual ao que foi adicionado)
    // Como o método remove usa equals() padrão, vamos remover pelo índice
    Item firstItem = shoppingCart.getItems().get(0);
    shoppingCart.removeItem(firstItem);
    
    // Verifica se o número de itens diminuiu
    assertEquals(1, shoppingCart.getItemCount());
    
    // Verifica se o item foi removido da lista
    assertFalse(shoppingCart.getItems().contains(firstItem));
  }
  @Test
  public void testGetTotalPrice() {
    // Testa se o preço total é calculado corretamente
    // O setUp() adiciona dois livros: ESM (65.0) + GoF (120.0) = 185.0
    double expectedTotal = 65.0 + 120.0;
    double actualTotal = shoppingCart.getTotalPrice();
    
    assertEquals(expectedTotal, actualTotal, 0.01); // delta para comparação de doubles
    
    // Testa com carrinho vazio
    shoppingCart.clearCart();
    assertEquals(0.0, shoppingCart.getTotalPrice(), 0.01);
    
    // Testa adicionando mais um item
    shoppingCart.addItem(new Item("Test Book", 50.0));
    assertEquals(50.0, shoppingCart.getTotalPrice(), 0.01);
  }
  @Test
  public void testClearCart() {
    // Testa se o carrinho é limpo corretamente
    // Verifica que inicialmente há itens no carrinho (do setUp)
    assertEquals(2, shoppingCart.getItemCount());
    
    // Limpa o carrinho
    shoppingCart.clearCart();
    
    // Verifica se o carrinho está vazio
    assertEquals(0, shoppingCart.getItemCount());
    assertTrue(shoppingCart.getItems().isEmpty());
    
    // Verifica se o preço total é zero
    assertEquals(0.0, shoppingCart.getTotalPrice(), 0.01);
  }
}
