package com.crm.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import com.crm.domain.Supplier;
import com.crm.repository.SupplierRepository;
import com.crm.requestDTO.OrdersUpdateRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.crm.domain.Orders;
import com.crm.exception.ResourceNotFoundException;
import com.crm.exception.message.ErrorMessage;
import com.crm.repository.OrdersRepository;
import com.crm.requestDTO.OrdersRequestDTO;
import com.crm.responseDTO.OrdersResponseDTO;

@Service
public class OrdersService {
    @Autowired
    private OrdersRepository ordersRepository;

    LocalDate today = LocalDate.now();
    @Autowired
    private SupplierRepository supplierRepository;
    @Autowired
    private SupplierService supplierService;

//*************   CREATE ORDER  *******************/

    public void createOrders(OrdersRequestDTO ordersRequestDTO) {
        Orders orders = new Orders();

        orders.setOrderAmount(ordersRequestDTO.getOrderAmount());
        orders.setRfq(ordersRequestDTO.getRfq());
        orders.setOrderQuantity(ordersRequestDTO.getOrderQuantity());
        //orders.setTotalWeight(ordersRequestDTO.getTotalWeight());
        //orders.setFreightCost(ordersRequestDTO.getFreightCost());
        //orders.setForwarder(ordersRequestDTO.getForwarder());
        orders.setEstimatedDeliveryDate(ordersRequestDTO.getEstimatedDeliveryDate());
        //orders.setDeliveryDate(ordersRequestDTO.getDeliveryDate());
        orders.setOrderDate(today);
        //orders.setProfit(ordersRequestDTO.getProfit());
        //orders.setProfitPercentage(ordersRequestDTO.getProfitPercentage());
        orders.setNotes(ordersRequestDTO.getNotes());
        orders.setShipping(ordersRequestDTO.getShipping());
        orders.setOrderStatus(ordersRequestDTO.getOrderStatus());
        orders.setTypeOfDelivery(ordersRequestDTO.getTypeOfDelivery());
        orders.setPackingArrangement(ordersRequestDTO.getPackingArrangement());
        orders.setOrderType(ordersRequestDTO.getOrderType());
        orders.setCurrencyType(ordersRequestDTO.getCurrencyType());
        orders.setPaymentMethod(ordersRequestDTO.getPaymentMethod());

        List suppliers = new ArrayList<>();
        for (Long supplierId:  ordersRequestDTO.getSupplierIds()  ) {
            Supplier supplier = supplierRepository.getSuplierById(supplierId);
            suppliers.add(supplier);
        }
        orders.setSuppliers(suppliers);

        ordersRepository.save(orders);


    }

//*************  GET ORDER  ******************************/

    public OrdersResponseDTO getOrderById(Long id) {
        Orders orders = ordersRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException(String.format(ErrorMessage.RESOURCE_NOT_FOUND_MESSAGE, id)));

        OrdersResponseDTO ordersResponseDTO = new OrdersResponseDTO();

        ordersResponseDTO.setOrderAmount(orders.getOrderAmount());
        ordersResponseDTO.setRfq(orders.getRfq());
        ordersResponseDTO.setOrderQuantity(orders.getOrderQuantity());
        ordersResponseDTO.setTotalWeight(orders.getTotalWeight());
        ordersResponseDTO.setFreightCost(orders.getFreightCost());
        ordersResponseDTO.setForwarder(orders.getForwarder());
        ordersResponseDTO.setEstimatedDeliveryDate(orders.getEstimatedDeliveryDate());
        ordersResponseDTO.setDeliveryDate(orders.getDeliveryDate());
        ordersResponseDTO.setOrderDate(today);
        ordersResponseDTO.setProfit(orders.getProfit());
        ordersResponseDTO.setProfitPercentage(orders.getProfitPercentage());
        ordersResponseDTO.setNotes(orders.getNotes());
        ordersResponseDTO.setShipping(orders.getShipping());
        ordersResponseDTO.setOrderStatus(orders.getOrderStatus());
        ordersResponseDTO.setTypeOfDelivery(orders.getTypeOfDelivery());
        ordersResponseDTO.setPackingArrangement(orders.getPackingArrangement());
        ordersResponseDTO.setOrderType(orders.getOrderType());
        ordersResponseDTO.setCurrencyType(orders.getCurrencyType());
        ordersResponseDTO.setPaymentMethod(orders.getPaymentMethod());
        
        return ordersResponseDTO;


    }

//*****************  GET ALL ORDERS  ***************/

    public List<OrdersResponseDTO> getAllOrders() {

        List<Orders> ordersList = ordersRepository.findAll();

        List<OrdersResponseDTO> dtoList = new ArrayList<>();

        for (Orders orders : ordersList) {

            OrdersResponseDTO ordersResponseDTO = new OrdersResponseDTO();
            ordersResponseDTO.setOrderAmount(orders.getOrderAmount());
            ordersResponseDTO.setRfq(orders.getRfq());
            ordersResponseDTO.setOrderQuantity(orders.getOrderQuantity());
            ordersResponseDTO.setTotalWeight(orders.getTotalWeight());
            ordersResponseDTO.setFreightCost(orders.getFreightCost());
            ordersResponseDTO.setForwarder(orders.getForwarder());
            ordersResponseDTO.setEstimatedDeliveryDate(orders.getEstimatedDeliveryDate());
            ordersResponseDTO.setDeliveryDate(orders.getDeliveryDate());
            ordersResponseDTO.setOrderDate(today);
            ordersResponseDTO.setProfit(orders.getProfit());
            ordersResponseDTO.setProfitPercentage(orders.getProfitPercentage());
            ordersResponseDTO.setNotes(orders.getNotes());
            ordersResponseDTO.setShipping(orders.getShipping());
            ordersResponseDTO.setOrderStatus(orders.getOrderStatus());
            ordersResponseDTO.setTypeOfDelivery(orders.getTypeOfDelivery());
            ordersResponseDTO.setPackingArrangement(orders.getPackingArrangement());
            ordersResponseDTO.setOrderType(orders.getOrderType());
            ordersResponseDTO.setCurrencyType(orders.getCurrencyType());
            ordersResponseDTO.setPaymentMethod(orders.getPaymentMethod());
            dtoList.add(ordersResponseDTO);
        }
        return dtoList;
    }

// ********* ORDERS UPDATE  ************************/ 

    public void updateOrder(Long id, OrdersUpdateRequestDTO ordersUpdateRequestDTO) {
        Orders order = ordersRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(String.format(ErrorMessage.ORDER_UPDATE_RESPONSE_MESSAGE, id)));
        ;


        order.setOrderAmount(ordersUpdateRequestDTO.getOrderAmount());
        order.setRfq(ordersUpdateRequestDTO.getRfq());
        order.setOrderQuantity(ordersUpdateRequestDTO.getOrderQuantity());
        order.setTotalWeight(ordersUpdateRequestDTO.getTotalWeight());
        order.setFreightCost(ordersUpdateRequestDTO.getFreightCost());
        order.setForwarder(ordersUpdateRequestDTO.getForwarder());
        order.setEstimatedDeliveryDate(ordersUpdateRequestDTO.getEstimatedDeliveryDate());
        order.setDeliveryDate(ordersUpdateRequestDTO.getDeliveryDate());
        order.setOrderDate(today);
        order.setProfit(ordersUpdateRequestDTO.getProfit());
        order.setProfitPercentage(ordersUpdateRequestDTO.getProfitPercentage());
        order.setNotes(ordersUpdateRequestDTO.getNotes());
        order.setShipping(ordersUpdateRequestDTO.getShipping());
        order.setOrderStatus(ordersUpdateRequestDTO.getOrderStatus());
        order.setTypeOfDelivery(ordersUpdateRequestDTO.getTypeOfDelivery());
        order.setPackingArrangement(ordersUpdateRequestDTO.getPackingArrangement());
        order.setOrderType(ordersUpdateRequestDTO.getOrderType());
        order.setCurrencyType(ordersUpdateRequestDTO.getCurrencyType());
        order.setPaymentMethod(ordersUpdateRequestDTO.getPaymentMethod());
        ordersRepository.save(order);
    }


//***********  PAGEABLE ORDERS  ***************************//

    public Page<OrdersResponseDTO> findAllWithPage(Pageable pageable) {

        Page<Orders> ordersPage = ordersRepository.findAll(pageable);
        Page<OrdersResponseDTO> responsePage = ordersPage.map(new Function<Orders, OrdersResponseDTO>() {


            @Override
            public OrdersResponseDTO apply(Orders orders) {

                OrdersResponseDTO ordersResponseDTO = new OrdersResponseDTO();

                ordersResponseDTO.setOrderAmount(orders.getOrderAmount());
                ordersResponseDTO.setRfq(orders.getRfq());
                ordersResponseDTO.setOrderQuantity(orders.getOrderQuantity());
                ordersResponseDTO.setTotalWeight(orders.getTotalWeight());
                ordersResponseDTO.setFreightCost(orders.getFreightCost());
                ordersResponseDTO.setForwarder(orders.getForwarder());
                ordersResponseDTO.setEstimatedDeliveryDate(orders.getEstimatedDeliveryDate());
                ordersResponseDTO.setDeliveryDate(orders.getDeliveryDate());
                ordersResponseDTO.setOrderDate(today);
                ordersResponseDTO.setProfit(orders.getProfit());
                ordersResponseDTO.setProfitPercentage(orders.getProfitPercentage());
                ordersResponseDTO.setNotes(orders.getNotes());
                ordersResponseDTO.setShipping(orders.getShipping());
                ordersResponseDTO.setOrderStatus(orders.getOrderStatus());
                ordersResponseDTO.setTypeOfDelivery(orders.getTypeOfDelivery());
                ordersResponseDTO.setPackingArrangement(orders.getPackingArrangement());
                ordersResponseDTO.setOrderType(orders.getOrderType());
                ordersResponseDTO.setCurrencyType(orders.getCurrencyType());
                ordersResponseDTO.setPaymentMethod(orders.getPaymentMethod());
                return ordersResponseDTO;

            }
        });

        return responsePage;


    }

//***********  DELETE ORDER  ***********************/

    public void deleteOrderById(Long id) {
        Orders order = ordersRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(String.format(ErrorMessage.ORDER_DELETED_MESSAGE, id)));
        ordersRepository.delete(order);
    }


}















