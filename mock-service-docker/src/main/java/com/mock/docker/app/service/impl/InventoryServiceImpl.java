package com.mock.docker.app.service.impl;

import com.mock.docker.app.model.Product;
import com.mock.docker.app.model.User;
import com.mock.docker.app.model.entities.PermissionTable;
import com.mock.docker.app.model.entities.ProductTable;
import com.mock.docker.app.model.entities.RoleTable;
import com.mock.docker.app.model.entities.UserTable;
import com.mock.docker.app.model.exceptions.InsufficientPermissionsException;
import com.mock.docker.app.model.exceptions.NoSuchProductException;
import com.mock.docker.app.model.responses.ProductInventoryResponse;
import com.mock.docker.app.repositories.PermissionRepository;
import com.mock.docker.app.repositories.ProductRepository;
import com.mock.docker.app.repositories.RoleRepository;
import com.mock.docker.app.repositories.UserRepository;
import com.mock.docker.app.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

import static com.mock.docker.app.model.PermissionType.READ;
import static com.mock.docker.app.model.PermissionType.WRITE;
import static com.mock.docker.app.model.ResourceType.PRODUCT_RESOURCE;

@Component
public class InventoryServiceImpl implements InventoryService {
    private UserRepository userRepository;
    private PermissionRepository permissionRepository;
    private RoleRepository roleRepository;
    private ProductRepository productRepository;

    @Autowired
    public InventoryServiceImpl(final UserRepository userRepository, final PermissionRepository permissionRepository,
                                final RoleRepository roleRepository, final ProductRepository productRepository) {
        this.userRepository = userRepository;
        this.permissionRepository = permissionRepository;
        this.roleRepository = roleRepository;
        this.productRepository = productRepository;
    }

    @Override
    public void addProduct(User user, Product product) throws InsufficientPermissionsException {
        if (!validateUserWithProductWriteAccess(user)) {
            throw new InsufficientPermissionsException("User doesn't have write permission for products");
        }
        if (Objects.nonNull(product)) {
            final ProductTable productTable = new ProductTable();
            productTable.cloneFromProduct(product);
            this.productRepository.saveAndFlush(productTable);
        }
    }

    @Override
    public void deleteProduct(User user, Product product) throws InsufficientPermissionsException {
        if (!validateUserWithProductWriteAccess(user)) {
            throw new InsufficientPermissionsException("User doesn't have write permission for products");
        }
        if (Objects.nonNull(product) && Objects.nonNull(product.getId())) {
            this.productRepository.deleteById(product.getId());
        }
    }

    @Override
    public ProductInventoryResponse getProductInventory(User user, Product product) throws InsufficientPermissionsException, NoSuchProductException {
        if (!validateUserWithProductReadAccess(user)) {
            throw new InsufficientPermissionsException("User doesn't have read permission for products");
        }
        if (Objects.nonNull(product) && Objects.nonNull(product.getId())) {
            final ProductTable productTable = this.productRepository.getById(product.getId());
            final Product productResponse = Product.builder()
                    .id(productTable.getId())
                    .productName(productTable.getProductName())
                    .productDescription(productTable.getProductDescription())
                    .price(productTable.getPrice())
                    .discount(productTable.getDiscount())
                    .build();
            final ProductInventoryResponse productInventoryResponse = ProductInventoryResponse.builder()
                    .product(productResponse)
                    .requestedByUser(user.getId())
                    .build();
            return productInventoryResponse;
        } else {
            throw new NoSuchProductException("No such product found");
        }
    }

    private boolean validateUserWithProductWriteAccess(final User user) {
        final UserTable userTable = this.userRepository.findById(user.getId()).get();
        final String permissionName = userTable.getPermissionName();
        final String roleName = userTable.getRoleName();
        if (validateProductWritePermission(permissionName)) {
            return true;
        }
        final List<RoleTable> roleTable = this.roleRepository.findAllByRoleName(roleName);
        for (RoleTable role : roleTable) {
            if (validateProductWritePermission(role.getPermissionName())) {
                return true;
            }
        }
        return false;
    }

    private boolean validateProductWritePermission(final String permissionName) {
        final List<PermissionTable> permissionTable = this.permissionRepository.findAllByPermissionName(permissionName);
        for (PermissionTable permission : permissionTable) {
            if (permission.getResourceType().equalsIgnoreCase(PRODUCT_RESOURCE.name())
                    && permission.getType().equalsIgnoreCase(WRITE.name())) {
                return true;
            }
        }
        return false;
    }

    private boolean validateUserWithProductReadAccess(final User user) {
        final UserTable userTable = this.userRepository.findById(user.getId()).get();
        final String permissionName = userTable.getPermissionName();
        final String roleName = userTable.getRoleName();
        if (validateProductReadPermission(permissionName)) {
            return true;
        }
        final List<RoleTable> roleTable = this.roleRepository.findAllByRoleName(roleName);
        for (RoleTable role : roleTable) {
            if (validateProductReadPermission(role.getPermissionName())) {
                return true;
            }
        }
        return false;
    }

    private boolean validateProductReadPermission(final String permissionName) {
        final List<PermissionTable> permissionTable = this.permissionRepository.findAllByPermissionName(permissionName);
        for (PermissionTable permission : permissionTable) {
            if (permission.getResourceType().equalsIgnoreCase(PRODUCT_RESOURCE.name())
                    && permission.getType().equalsIgnoreCase(READ.name())) {
                return true;
            }
        }
        return false;
    }
}
