package weber.logistics.module.car.service;

import weber.logistics.framework.service.BaseService;
import weber.logistics.module.car.model.TripTraffic;

public interface TripTrafficService extends BaseService<TripTraffic> {

	TripTraffic getByTrip(Long tripId);

	int cancel(TripTraffic tripTraffic);
}
