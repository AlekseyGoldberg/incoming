package com.example.incoming.repository.Impl;

import com.example.incoming.entity.Invest;
import com.example.incoming.entity.QInvest;
import com.example.incoming.entity.User;
import com.example.incoming.repository.InvestConnection;
import com.querydsl.jpa.impl.JPAQuery;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@AllArgsConstructor
public class InvestRepositoryImpl {
    private InvestConnection investConnection;
    private EntityManager entityManager;

    public void saveInvest(Invest invest) {
        investConnection.save(invest);
    }

    public List<Invest> getInvests(User user) {
        return investConnection.findAllByUser(user);
    }

    public double getTotalAmountInvest(User user) {
        JPAQuery<Invest> query = new JPAQuery<>(entityManager);
        QInvest invest = QInvest.invest;
        JPAQuery<Double> result = query.select(invest.sumInvest.sum()).from(invest)
                .where(invest.user.eq(user));
        return result.fetch().get(0) == null ? 0 : result.fetch().get(0);
    }

    public Invest getInvest(User user, Long id) {
        return investConnection.findByIdAndUser(id, user);
    }

    public void deleteInvest(Invest invest) {
        investConnection.delete(invest);
    }

}
