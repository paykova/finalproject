package org.softuni.finalpoject.service;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.softuni.finalpoject.domain.entities.User;
import org.softuni.finalpoject.domain.models.service.RoleServiceModel;
import org.softuni.finalpoject.domain.models.service.UserServiceModel;
import org.softuni.finalpoject.repository.RoleRepository;
import org.softuni.finalpoject.repository.UserRepository;
import org.softuni.finalpoject.utils.ValidationUtil;
import org.softuni.finalpoject.utils.ValidationUtilImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class UserServiceImplTest {

    private static final String INVALIT_ID = "Invalid ID";
    private static final String INVALIT_USERNAME = "Invalid Username";
    private static final String INVALIT_CRITERIA = "Invalid Criteria";
    private static final String INVALID_ROLES = "Invalid Role";
    private UserService userService;
    private UserServiceModel testUser;
    private ModelMapper modelMapper;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private RoleService roleService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    private ValidationUtil validationUtil;

    @Before
    public void before() {
        this.validationUtil = new ValidationUtilImpl();
        this.modelMapper = new ModelMapper();
        this.bCryptPasswordEncoder = new BCryptPasswordEncoder();
        this.roleService = new RoleServiceImpl(this.roleRepository, this.modelMapper);
      // this.userService = new UserServiceImpl(this.userRepository, this.roleService, this.modelMapper, this.bCryptPasswordEncoder, this.validationUtil);
//		this.testUser = createTestUser();
    }


    @Test(expected = Exception.class)
    public void test_registerUser_with_null_data_then_returnExeption() {
        UserServiceModel toBeSaveUser = getUserServiceModelWithNullField();
        this.userService.registerUser(toBeSaveUser);
    }

    @Test(expected = Exception.class)
    public void test_registerUser_with_same_username_then_returnExeption() {
        UserServiceModel toBeSaveUser = getUserServiceModel();

        UserServiceModel actual = this.userService.registerUser(toBeSaveUser);
        UserServiceModel expected = modelMapper.map(userRepository.findById(actual.getId()).orElse(null), UserServiceModel.class);
        actual = this.userService.registerUser(expected);
        assertEquals(null, actual.getId());

    }

    @Test(expected = Exception.class)
    public void test_registerUser_with_same_email_then_returnExeption() {
        UserServiceModel toBeSaveUser = getUserServiceModel();

        UserServiceModel actual = this.userService.registerUser(toBeSaveUser);
        UserServiceModel expected = modelMapper.map(userRepository.findById(actual.getId()).orElse(null), UserServiceModel.class);
        actual = this.userService.registerUser(expected);
        assertEquals(null, actual.getId());

    }


    @Test(expected = Exception.class)
    public void test_findByUsername_with_incorrect_data_then_ReturnExeption() {
        UserServiceModel testUser = getUserServiceModel();
        this.userService.registerUser(testUser);
        this.userService.findUserByUserName(INVALIT_USERNAME);
    }


    @Test(expected = Exception.class)
    public void test_setUserRole_with_incorrect_roles_then_ReturnExeption() {
        UserServiceModel actual = this.userService.registerUser(getUserServiceModel());
        this.userService.setUserRole(actual.getId(), INVALID_ROLES);

    }

    private UserServiceModel getUserServiceModel() {
        UserServiceModel testUser = new UserServiceModel();
        testUser.setUsername("Petura");
        testUser.setPassword("123456");
        testUser.setEmail("valid@mail.com");

        return testUser;
    }

    private UserServiceModel getUserServiceModelWithNullField() {
        UserServiceModel testUser = new UserServiceModel();
        testUser.setUsername("Petura");
        testUser.setPassword("123456");
        testUser.setEmail("valid@mail.com");

        return testUser;
    }

    private User getUser() {
        User testUser = new User();
        testUser.setUsername("Petura");
        testUser.setPassword("123456");
        testUser.setEmail("valid@mail.com");

        return testUser;
    }

    private RoleServiceModel createRole() {
        RoleServiceModel r = new RoleServiceModel();

        r.setAuthority("ROLE_USER");

        return r;
    }
}
