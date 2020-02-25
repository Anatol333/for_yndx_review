package com.kozhukhar.carshop_online.db.storage;

import com.kozhukhar.carshop_online.db.dao.impl.ProductDaoImpl;
import com.kozhukhar.carshop_online.db.dto.Product;
import com.kozhukhar.carshop_online.db.transact.TransactionManager;
import com.kozhukhar.carshop_online.exception.AppException;
import com.kozhukhar.carshop_online.exception.Messages;
import com.kozhukhar.carshop_online.web.resource_tag.FieldTags;

import java.util.List;

public class ProductStorage {

    private String query;

    private TransactionManager transactionManager;

    public ProductStorage(String query, TransactionManager transactionManager) {
        this.query = query;
        this.transactionManager = transactionManager;
    }

    public List<Product> getProductsByPage(Integer page, Integer pageLen, ProductDaoImpl productDaoImpl) throws AppException {
        isFailedQuery();
        return transactionManager.execute(() -> productDaoImpl
                .getAllByQuery(query + " limit " + page * pageLen + ", " + pageLen + System.lineSeparator())
        );
    }

    public List<Product> getAllProducts(ProductDaoImpl productDaoImpl) throws AppException {
        isFailedQuery();
        return transactionManager.execute(() -> productDaoImpl.getAllByQuery(query));
    }

    private void isFailedQuery() throws AppException {
        if (query == null) {
            throw new AppException(Messages.CANNOT_GET_ALL_PRODUCTS_FROM_CATALOG);
        }
    }

    public static class QueryBuilder {

        private String query;

        public QueryBuilder() {
            query = "SELECT DISTINCT P.id, P.name, P.price, C.name as 'category_name', PR.name as 'producer_name'  \n" +
                    "FROM products P, producers PR, categories C\n" +
                    "WHERE PR.id = P.producer_id\n" +
                    "AND C.id = P.category_id";
        }

        public QueryBuilder byName(String name) {
            if (name != null) {
                query += " AND P.name LIKE '%" + name + "%' " + System.lineSeparator();
            }
            return this;
        }

        public QueryBuilder byCategories(String[] categories) {
            if (categories != null) {
                StringBuilder partQuery = new StringBuilder();
                partQuery.append(" AND (");
                for (int i = 0; i < categories.length; ++i) {
                    partQuery.append(" C.name = '").append(categories[i]).append("'").append(System.lineSeparator());
                    if (i != categories.length - 1) {
                        partQuery.append(" OR ");
                    }
                }
                partQuery.append(")").append(System.lineSeparator());
                query += partQuery.toString();
            }
            return this;
        }

        public QueryBuilder byProducers(String[] producers) {
            if (producers != null) {
                StringBuilder partQuery = new StringBuilder();
                partQuery.append(" AND (");
                for (int i = 0; i < producers.length; ++i) {
                    partQuery.append(" PR.name = '").append(producers[i]).append("'").append(System.lineSeparator());
                    if (i != producers.length - 1) {
                        partQuery.append(" OR ");
                    }
                }
                partQuery.append(")").append(System.lineSeparator());
                query += partQuery.toString();
            }
            return this;
        }

        public QueryBuilder byPrice(String priceFrom, String priceTo) {
            if (priceFrom != null && priceTo != null) {
                if (!priceFrom.equals(FieldTags.EMPTY_STRING) && !priceTo.equals(FieldTags.EMPTY_STRING)) {
                    query += " AND P.price BETWEEN " + priceFrom + " AND " + priceTo + System.lineSeparator();
                } else if (priceFrom.equals(FieldTags.EMPTY_STRING) && !priceTo.equals(FieldTags.EMPTY_STRING)) {
                    query += " AND P.price < " + priceTo + System.lineSeparator();
                } else if (!priceFrom.equals(FieldTags.EMPTY_STRING)) {
                    query += " AND P.price > " + priceFrom + System.lineSeparator();
                }

            }
            return this;
        }

        public QueryBuilder sort(String type) {
            if (type != null) {
                if (type.equals(SortType.NAME.getName())) {
                    query += " ORDER BY name " + System.lineSeparator();
                } else if (type.equals(SortType.PRICE.getName())) {
                    query += " ORDER BY price " + System.lineSeparator();
                } else if (type.equals(SortType.NAME_DESC.getName())) {
                    query += " ORDER BY name DESC " + System.lineSeparator();
                } else if (type.equals(SortType.PRICE_DESC.getName())) {
                    query += " ORDER BY price DESC " + System.lineSeparator();
                } else {
                    query += " ORDER BY id DESC " + System.lineSeparator();
                }
            } else {
                query += " ORDER BY name " + System.lineSeparator();
            }

            return this;
        }

        public ProductStorage build(TransactionManager transactionManager) {
            return new ProductStorage(query, transactionManager);
        }

    }

}
