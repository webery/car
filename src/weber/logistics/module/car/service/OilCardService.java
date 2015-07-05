package weber.logistics.module.car.service;

import weber.logistics.framework.service.BaseService;
import weber.logistics.module.car.model.OilCard;

public interface OilCardService extends BaseService<OilCard> {
	
	public OilCard findByCode(String code);
	
}
