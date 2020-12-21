package com.shuailee.provider;

import com.shuailee.IPay;
import org.springframework.stereotype.Service;

public class PayImpl implements IPay {
    @Override
    public Boolean payService(int amount) {
        System.out.println("发起支付" + amount);
        return true;
    }
}
