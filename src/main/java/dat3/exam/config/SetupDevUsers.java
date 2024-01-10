package dat3.exam.config;

import dat3.exam.entity.Member;
import dat3.exam.repository.MemberRepository;
import dat3.security.entity.Role;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import dat3.security.repository.UserWithRolesRepository;

@Controller
public class SetupDevUsers implements ApplicationRunner {

    MemberRepository memberRepository;
    UserWithRolesRepository userWithRolesRepository;
    PasswordEncoder passwordEncoder;
    String passwordUsedByAll;

    public SetupDevUsers(MemberRepository memberRepository, PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        this.memberRepository = memberRepository;
        passwordUsedByAll = "test12";
    }

    @Override
    public void run(ApplicationArguments args) {
        setupUserWithRoleUsers();
    }

    private void setupUserWithRoleUsers() {
        Member member1 = new Member("member1", passwordUsedByAll, "member1@a.dk", "Member1");
        member1.addRole(Role.USER);
        memberRepository.save(member1);
    }
}
