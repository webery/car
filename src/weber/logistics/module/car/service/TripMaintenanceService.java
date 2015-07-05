package weber.logistics.module.car.service;

import weber.logistics.framework.service.BaseService;
import weber.logistics.module.car.model.TripMaintenance;

public interface TripMaintenanceService extends BaseService<TripMaintenance> {

	TripMaintenance getByTrip(Long tripId);

	int cancel(TripMaintenance tripMaintenance);
}
