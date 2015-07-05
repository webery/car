package weber.logistics.module.car.service;

import weber.logistics.framework.service.BaseService;
import weber.logistics.module.car.model.Bracket;

public interface BracketService extends BaseService<Bracket> {

	Bracket findByPlateNum(String plateNum);
}
