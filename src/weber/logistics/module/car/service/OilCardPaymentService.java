package weber.logistics.module.car.service;

import java.util.List;

import weber.logistics.framework.page.domain.PageBounds;
import weber.logistics.framework.service.BaseService;
import weber.logistics.module.car.model.OilCardPayment;

public interface OilCardPaymentService extends BaseService<OilCardPayment> {

	List<OilCardPayment> findByTrip(Long tripId);

	List<OilCardPayment> findByConditionPage(OilCardPayment oilCardPayment,
			String yearMonth, PageBounds pageBound);
}
