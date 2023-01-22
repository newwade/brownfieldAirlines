package com.brownfield.app.service;

import com.brownfield.app.entity.Checkin;

public interface CheckinService {

    Checkin saveChecking(Checkin checkin);
    Checkin findByCheckinId(long checkinId);

}
