package hellojpa.embedded;

import hellojpa.PhoneSerivceProvider;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable
public class PhoneNumber {
    String areaCode;
    String localNumber;

    @ManyToOne
    PhoneSerivceProvider provider;  // 엔티티 참조

    public PhoneNumber() {
    }

    public String getAreaCode() {
        return areaCode;
    }

    public String getLocalNumber() {
        return localNumber;
    }

    public PhoneSerivceProvider getProvider() {
        return provider;
    }
}
