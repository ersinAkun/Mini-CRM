package com.crm.controller;

import java.util.List;
import javax.validation.Valid;

import com.crm.requestDTO.OrdersUpdateRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.crm.requestDTO.OrdersRequestDTO;
import com.crm.responseDTO.CrmResponse;
import com.crm.responseDTO.OrdersResponseDTO;
import com.crm.responseDTO.ResponseMessage;
import com.crm.service.OrdersService;

@RestController
@RequestMapping("/order")
public class OrdersController {
    @Autowired
    private OrdersService ordersService;



// ***********  CREATE ORDER  *****************/

    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<CrmResponse> updateLead(@Valid  @RequestBody OrdersRequestDTO ordersRequestDTO) {
        ordersService.createOrders(ordersRequestDTO);

        CrmResponse crmResponse = new CrmResponse();
        crmResponse.setMessage(ResponseMessage.ORDER_CREATE_RESPONSE);
        crmResponse.setSuccess(true);
        return ResponseEntity.ok(crmResponse);

    }
// *********  GET ORDER *****************/

    @GetMapping("/getOrder/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole ('USER')")
    public ResponseEntity<OrdersResponseDTO> getOrderById(@PathVariable Long id){
        OrdersResponseDTO ordersResponseDTO= ordersService.getOrderById(id);
        return ResponseEntity.ok(ordersResponseDTO);
    }

//***********  GET ALL ORDERS  ******************/

    @GetMapping("/getAllOrders")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<List<OrdersResponseDTO>> getAllOrders() {

        List<OrdersResponseDTO> allOrders = ordersService.getAllOrders();

        return ResponseEntity.ok(allOrders);
    }

// **************  UPDATE ORDER  ****************/

    @PutMapping("/update/{oid}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<CrmResponse> updateLead(@Valid @PathVariable("oid") Long oid, @RequestBody OrdersUpdateRequestDTO ordersUpdateRequestDTO) {
        ordersService.updateOrder(oid, ordersUpdateRequestDTO);
        CrmResponse crmResponse = new CrmResponse(ResponseMessage.ORDER_UPDATED_MESSAGE, true);
        return ResponseEntity.ok(crmResponse);
    }

//*************  PAGEABLE ORDERS  ***********************

    @GetMapping("/ordersPages")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<Page<OrdersResponseDTO>> getAllOrdersWithPage(

            @RequestParam("page") int page,
            @RequestParam("size") int size,
            @RequestParam("sort") String prop,

            @RequestParam(value = "direction",
                    required = false,
                    defaultValue = "DESC") Direction direction) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, prop));

        Page<OrdersResponseDTO> pageDTO = ordersService.findAllWithPage(pageable);
        return ResponseEntity.ok(pageDTO);
    }


//****************  OREDR DELETE  ***********************//

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<CrmResponse> deleteLead(@PathVariable Long id){
        ordersService.deleteOrderById(id);
        CrmResponse response = new CrmResponse(ResponseMessage.ORDER_DELETED_MESSAGE,true);
        return ResponseEntity.ok(response);
    }


}