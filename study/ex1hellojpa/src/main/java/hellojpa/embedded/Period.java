package hellojpa.embedded;

import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Embeddable
public class Period {

    @Temporal(TemporalType.DATE)
    Date startDate;

    @Temporal(TemporalType.DATE)
    Date endDate;

    public Period() {   // 임베디드 타입은 기본 생성자가 필수
    }

    public boolean isWork(Date date) {
        if (date.before(endDate) && date.after(startDate))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
