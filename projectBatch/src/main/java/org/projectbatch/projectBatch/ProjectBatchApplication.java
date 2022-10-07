package org.projectbatch.projectBatch;

import org.projectbatch.projectBatch.entity.Role;
import org.projectbatch.projectBatch.entity.User;
import org.projectbatch.projectBatch.repository.RoleRepository;
import org.projectbatch.projectBatch.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SpringBootApplication
public class ProjectBatchApplication {
	@Autowired
	PasswordEncoder passwordEncoder;
	public static void main(String[] args) {
		SpringApplication.run(ProjectBatchApplication.class, args);
	}
	@Bean
	public CommandLineRunner demoData(RoleRepository repo, UserRepository userRepository) {
		return args -> {
			List<Role> roles = repo.findAll();
			if (roles.stream().count() == 0) {
				Role role1 = new Role();
				Role role2 = new Role();
				Role role3 = new Role();
				role1.setName("ROLE_CLIENT");
				role2.setName("ROLE_MODERATOR");
				role3.setName("ROLE_ADMIN");
				repo.save(role1);
				repo.save(role2);
				repo.save(role3);
				List<User> users=userRepository.findAll();
				if (users.stream().count() == 0) {
					User user=new User();
					user.getRoles().add(role3);
					user.setUsername("webper");
					user.setPassword(passwordEncoder.encode("sawebper"));
					userRepository.save(user);
				}
			}
		};
	}
}
