import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

public class MemberTest{
    
    @Test
    void testConstructor(){
        Member member = new Member("Alice","ID001");
        assertEquals("Alice", member.getName());
        assertEquals("ID001", member.getMemberId());
    }
}
