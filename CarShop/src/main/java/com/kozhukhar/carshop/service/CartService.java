package com.kozhukhar.carshop.service;

import com.kozhukhar.carshop.dao.impl.CartEntryDaoImpl;
import com.kozhukhar.carshop.dao.impl.TransportDaoImpl;
import com.kozhukhar.carshop.entity.bean.CartEntry;
import com.kozhukhar.carshop_online.exception.AppException;
import com.kozhukhar.carshop_online.exception.DBException;
import com.kozhukhar.carshop_online.exception.Messages;
import com.kozhukhar.carshop.storage.CartStore;
import com.kozhukhar.carshop.storage.CatalogStore;
import com.kozhukhar.carshop.storage.LastFiveProductStore;
import com.kozhukhar.carshop.storage.OrderStore;
import com.kozhukhar.carshop.util.LocalDateUtil;
import com.kozhukhar.carshop.view.DrawTable;
import org.apache.log4j.Logger;

import java.time.LocalDateTime;
import java.util.List;

public class CartService {

    private LastFiveProductStore cartLastFive;

    private OrderStore orderStore;

    private CartStore cartStore;

    private CatalogStore catalogStore;

    public CartService() {
    }

    public CartService(LastFiveProductStore cartLastFive, OrderStore orderStore, CartStore cartStore, CatalogStore catalogStore) {
        this.cartLastFive = cartLastFive;
        this.orderStore = orderStore;
        this.cartStore = cartStore;
        this.catalogStore = catalogStore;
    }

    private static final Logger LOG = Logger.getLogger(CartService.class);

    public void addProductToCart(String name) throws AppException {
        CartEntryDaoImpl productDaoImpl = new CartEntryDaoImpl(cartStore, catalogStore);
        TransportDaoImpl transportDaoImpl = new TransportDaoImpl(catalogStore);
        try {
            CartEntry cartEntry = (CartEntry) transportDaoImpl.get(name);
            if (cartEntry.getObjectOfProduct() == null)
                throw new AppException(Messages.CANNOT_ADD_PRODUCT_TO_CART_NOT_EXIST_PRODUCT);
            productDaoImpl.save(cartEntry);
            cartLastFive.addNewProduct(cartEntry);
        } catch (Exception ex) {
            throw new AppException(ex.getMessage());
        }
    }

    public Integer orderProductsFromCart() throws AppException {
        CartEntryDaoImpl productDaoImpl = new CartEntryDaoImpl(cartStore, catalogStore);
        try {
            List<CartEntry> productsInCart = productDaoImpl.getAll();
            if (productsInCart.size() == 0) throw new AppException();
            Integer fullPrice = orderStore.addNewOrder(productsInCart);
            productDaoImpl.deleteAll();
            return fullPrice;
        } catch (DBException ex) {
            throw new AppException(Messages.CANNOT_GET_ALL_PRODUCTS_FROM_CART);
        } catch (AppException ex) {
            throw new AppException(Messages.CART_HAVE_NOT_PRODUCTS);
        } catch (Exception ex) {
            throw new AppException(Messages.CANNOT_ORDER_PRODUCTS_FROM_BASKET);
        }
    }

    public String showCartInfo() throws DBException {
        LOG.debug("Showing cart in service.");
        CartEntryDaoImpl productDaoImpl = new CartEntryDaoImpl(cartStore, catalogStore);
        return DrawTable.drawCartTable(productDaoImpl.getAll());
    }

    public String showOrderInfo(String dateFilter) throws AppException {
        try {
            String outOrderInfo = DrawTable.drawHistoryTable(orderStore.getOrderHistory());
            if (dateFilter != null) {
                String[] datesFromTo = dateFilter.split("-");
                if (datesFromTo.length == 1) {
                    outOrderInfo = getNearestFromHistory(datesFromTo[0], orderStore);
                } else {
                    outOrderInfo = getAllBetweenDates(datesFromTo, orderStore);
                }

            }
            return outOrderInfo;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new AppException(Messages.DATE_FORMAT_IS_WRONG);
        }
    }

    private String getAllBetweenDates(String[] datesFromTo, OrderStore orderStore) {
        String outOrderInfo = "";
        LocalDateTime dateStart = LocalDateUtil.dateFormatter(datesFromTo[0]);
        LocalDateTime dateEnd = LocalDateUtil.dateFormatter(datesFromTo[1]);
        outOrderInfo = DrawTable.drawHistoryTable(
                orderStore.getOrderHistoryByDate(dateStart, dateEnd)
        );
        return outOrderInfo;
    }

    private String getNearestFromHistory(String datesFrom, OrderStore orderStore) throws AppException {
        String outOrderInfo = "";
        LocalDateTime date = LocalDateUtil.dateFormatter(datesFrom);
        outOrderInfo = DrawTable.drawHistoryTable(
                orderStore.getOrderHistoryByDateOne(date)
        );
        return outOrderInfo;
    }

    public String showLastFiveInfo() throws AppException {
        StringBuilder stringBuilder = new StringBuilder();
        cartLastFive.getLastFiveProduct().forEach((k, v) -> stringBuilder.append(k).append(System.lineSeparator()));
        String lastFiveOutput = stringBuilder.toString();
        if (lastFiveOutput.length() == 0)
            throw new AppException(Messages.NOTHING_ELEMENT_WAS_ADDED_TO_CART);
        return lastFiveOutput;
    }

}
