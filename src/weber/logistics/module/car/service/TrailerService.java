package weber.logistics.module.car.service;

import weber.logistics.framework.service.BaseService;
import weber.logistics.module.car.model.Trailer;

public interface TrailerService extends BaseService<Trailer> {

	Trailer findByPlateNum(String plateNum);

	Trailer findByEmp(String empId);

	Trailer findByContainer(String containerId);

	Trailer findByBracket(String bracketId);
}
