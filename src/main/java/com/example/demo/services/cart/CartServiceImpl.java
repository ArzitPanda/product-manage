package com.example.demo.services.cart;

import com.example.demo.models.Cart;
import com.example.demo.models.CartItem;
import com.example.demo.models.Product;
import com.example.demo.models.User;
import com.example.demo.repositories.CartItemRepository;
import com.example.demo.repositories.CartRepository;
import com.example.demo.repositories.ProductRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.LoggedUserContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class CartServiceImpl implements   CartService{
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LoggedUserContext loggedUserContext;

    @Override
    @Transactional
    public void addToCart(Long userId, Long productId, int quantity) {
        Cart cart = getCartByUserId(userId);
        Product product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product not found"));
        if(product.getStock()<quantity)
        {
            throw  new RuntimeException("stock is not present only "+product.getStock()+ " left");
        }


        List<CartItem> cartItems =cart.getCartItems();
        CartItem cartItem;

        if(cartItems!=null)
        {
           cartItem= cartItems.stream()
                    .filter(item -> item.getProduct().getId().equals(productId))
                    .findFirst()
                    .orElse(new CartItem());

        }
        else
        {
            cart.setCartItems(new ArrayList<>());
            cartItem=new CartItem();
        }


        cartItem.setProduct(product);
        cartItem.setQuantity(cartItem.getQuantity() + quantity);
        cartItem.setCart(cart);

        cart.getCartItems().add(cartItem);
        cart.setTotal(cart.getTotal()+(cartItem.getQuantity()*product.getPrice()));
        cartRepository.save(cart);
    }

    @Override
    @Transactional
    public void deleteCartItem(Long cartItemId) {
      Long userIdAuthenticate =  loggedUserContext.getUser().getId();
      Cart cart = getCartByUserId(userIdAuthenticate);
      CartItem cartItem = cartItemRepository.findById(cartItemId).orElseThrow(()->new RuntimeException("not found"));
        List<CartItem>  cartItems = cart.getCartItems();
        if(cartItems.contains(cartItem))
        {       cart.setTotal(cart.getTotal()-cartItem.getQuantity()*(cartItem.getProduct().getPrice()));
            cartItemRepository.deleteById(cartItemId);

            cartRepository.save( cart);

        }




    }

    @Override
    @Transactional
    public void updateCartItemQuantity(Long cartItemId, int quantity) {


        Long userIdAuthenticate =  loggedUserContext.getUser().getId();
        Cart cart = getCartByUserId(userIdAuthenticate);
        CartItem cartItem = cartItemRepository.findById(cartItemId).orElseThrow(()->new RuntimeException("not found"));
        List<CartItem>  cartItems = cart.getCartItems();
        if(cartItems.contains(cartItem) && cartItem.getProduct().getStock()>=quantity)
        {
            cartItem.setQuantity(quantity);

            double price = cartItems.stream()
                    .filter(elem -> !elem.getId().equals(cartItemId))
                    .mapToDouble(ele -> ele.getQuantity() * ele.getProduct().getPrice())
                    .sum();

            price+= cartItem.getQuantity()*(cartItem.getProduct().getPrice());

            cart.setTotal(price);
            cartRepository.save(cart);

            cartItemRepository.save(cartItem);

        }


    }

    @Override
    @Transactional
    public void removeAllItemsFromCart(Long userId) {
        Cart cart = getCartByUserId(userId);
        cart.getCartItems().clear();
        cartRepository.save(cart);
    }

    @Override
    public Cart getCartByUserId(Long userId) {
       Cart cart = cartRepository.findByUserId(userId).orElse(null);
       if(cart==null)
       {
           User user = userRepository.findById(userId).orElseThrow(()->new RuntimeException("user not found"));
           user.setCart(new Cart());
           user.getCart().setUser(user);
           user.getCart().setTotal(0.0);
         User fetchedUser =   userRepository.save(user);

         Cart newCart = fetchedUser.getCart();
         return  newCart;
       }
       return  cart;

    }
}
