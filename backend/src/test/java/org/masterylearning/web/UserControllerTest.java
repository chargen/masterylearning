package org.masterylearning.web;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.masterylearning.domain.User;
import org.masterylearning.domain.ValidationIssue;
import org.masterylearning.domain.ValidationResult;
import org.masterylearning.dto.in.CreateUserDto;
import org.masterylearning.dto.out.CreateUserOutDto;
import org.masterylearning.service.UserService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

@RunWith(BlockJUnit4ClassRunner.class)
public class UserControllerTest {

    @InjectMocks
    private UserController userController = new UserController ();

    @Mock
    private UserService userService = new UserService ();

    @Before
    public void before () {
        MockitoAnnotations.initMocks (this);
    }

    /**
     * Test that a default username is generated if none is given.
     */
    @Test
    public void testDefaultUsernameGeneration () {

        CreateUserDto dto = new CreateUserDto ();

        String generatedUsername = "user1234567";

        User user = new User (null, null, generatedUsername, null);

        user.id = 1L;

        ValidationResult result = new ValidationResult ();
        result.valid = true;
        result.issue = ValidationIssue.USERNAME_MISSING;

        when (userService.validateCreateUserDto (dto)).thenReturn (result);

        when (userService.generateDefaultUsername ()).thenReturn (generatedUsername);

        when (userService.createUser (dto)).thenReturn (user);

        CreateUserOutDto outDto = userController.createUser (dto);

        verify (userService, times (1)).generateDefaultUsername ();
        verify (userService, times (1)).createUser (dto);

        Assert.assertTrue (dto.username != null);

        Assert.assertTrue (outDto.userId != null);
    }

}
