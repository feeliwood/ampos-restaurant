package ampos.restaurant.service;

import ampos.restaurant.domain.dto.*;
import ampos.restaurant.exception.ApplicationException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Bill and Bill Item Service
 *
 */
public interface BillService extends GenericService<BillRequestDTO, BillDTO, Long> {
     /**
     * Get bill and bill item report
     * 
     * @return
     * @throws ApplicationException
     */
    TotalReportDTO getBillAndBillItemReport() throws ApplicationException;
}