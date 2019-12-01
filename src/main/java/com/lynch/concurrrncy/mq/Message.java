package com.lynch.concurrrncy.mq;

import lombok.Data;

import java.util.Date;

/**
 * Created by lynch on 2019-12-01.
 **/
@Data
public class Message {
    private Long id;
    private String msg;
    private Date sendTime;

}
