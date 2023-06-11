package com.example.incoming.repository.Impl;

import com.example.incoming.entity.Debt;
import com.example.incoming.entity.QDebt;
import com.example.incoming.entity.User;
import com.example.incoming.repository.DebtConnection;
import com.querydsl.jpa.impl.JPAQuery;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@AllArgsConstructor
public class DebtRepositoryImpl {
    private DebtConnection debtConnection;
    private EntityManager entityManager;

    public void saveDebt(Debt debt) {
        debtConnection.save(debt);
    }

    public List<Debt> getListDebt(User user) {
        return debtConnection.findAllByUser(user);
    }

    public Double getTotalAmount(User user) {
        JPAQuery<Debt> query = new JPAQuery<>(entityManager);
        QDebt debt = QDebt.debt;
        JPAQuery<Double> result = query.select(debt.sum_debt.sum()).from(debt)
                .where(debt.user.eq(user));
        return result.fetch().get(0) == null ? 0 : result.fetch().get(0);
    }

    public Debt getDebt(User user, Long id) {
        return debtConnection.findByUserAndId(user, id);
    }

    public void deleteDebt(Debt debt) {
        debtConnection.delete(debt);
    }
}
