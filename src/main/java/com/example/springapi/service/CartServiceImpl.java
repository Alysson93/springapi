package com.example.springapi.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.springapi.domain.entity.Cart;
import com.example.springapi.domain.entity.Client;
import com.example.springapi.domain.entity.Item;
import com.example.springapi.domain.entity.Product;
import com.example.springapi.domain.repository.CartRepository;
import com.example.springapi.domain.repository.ClientRepository;
import com.example.springapi.domain.repository.ItemRepository;
import com.example.springapi.domain.repository.ProductRepository;
import com.example.springapi.exceptions.RegraNegocioException;
import com.example.springapi.rest.dto.CartDTO;
import com.example.springapi.rest.dto.ItemDTO;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    
    private final CartRepository repository;
    private final ClientRepository clientRepository;
    private final ProductRepository productRepository;
    private final ItemRepository itemRepository;

    @Override
    @Transactional
    public Cart save(CartDTO dto) {
        Cart cart = new Cart();
        cart.setTotal(dto.getTotal());
        cart.setCreatedAt(LocalDate.now());

        Integer clientId = dto.getClient();
        Client client = clientRepository.findById(clientId).orElseThrow(
            () -> new RegraNegocioException("Código de cliente inválido.")
        );
        cart.setClient(client);

        List<Item> items = convertItems(cart, dto.getItems());
        itemRepository.saveAll(items);
        cart.setItems(items);

        repository.save(cart);
        return cart;
    }

    @Override
    public Optional<Cart> getCart(Integer id) {
        Optional<Cart> cart = repository.findByIdFetchItems(id);
        return cart;
    }

    private List<Item> convertItems(Cart cart, List<ItemDTO> dto) {
        if (dto.isEmpty())
            throw new RegraNegocioException("Não há nenhum item neste pedido.");
        return dto.stream().map(row -> {
            Item item = new Item();
            item.setQtd(row.getQtd());
            item.setCart(cart);
            Integer productId = row.getProduct();
            Product product = productRepository.findById(productId).orElseThrow(
                () -> new RegraNegocioException("Código de produto inválido: " + productId)
            );
            item.setProduct(product);
            return item;
        }).collect(Collectors.toList());
    }


}
