package com.fairymo.macrunnerpickupsystem.network;

import com.fairymo.macrunnerpickupsystem.entity.BaseEntity;
import com.fairymo.macrunnerpickupsystem.entity.OptionStatus;
import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

import java.util.List;

public interface CallingService {

	@GET("/api/INVOICES/pickup_codes")
	Observable<Response<BaseEntity<OptionStatus>>> getOptions(@Query("shop_no") String shopNo, @Query("brand_no") String brandNo, @Query("runner_state") String runnerState);
}