package pl.wsb.students.repository.impl;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import pl.wsb.students.exceptions.ApiException;
import pl.wsb.students.hibernate.ApiToken;
import pl.wsb.students.hibernate.UserAccount;
import pl.wsb.students.repository.AbstractRepository;
import pl.wsb.students.repository.EntityManagerHelper;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Date;
import java.util.List;

public class ApiTokenRepository extends AbstractRepository<ApiToken, Integer> {
    @Override
    protected Class<ApiToken> getPersistentClass() {
        return ApiToken.class;
    }

    public static ApiToken findByAccessToken(String token) {
        if (StringUtils.isBlank(token)) {
            return null;
        } //if
        CriteriaBuilder criteriaBuilder = EntityManagerHelper.entityManager().getCriteriaBuilder();
        CriteriaQuery<ApiToken> criteriaQuery = criteriaBuilder.createQuery(ApiToken.class);
        Root<ApiToken> tokens = criteriaQuery.from(ApiToken.class);
        criteriaQuery.where(
                criteriaBuilder.equal(
                    criteriaBuilder.lower(tokens.<String>get("accessToken")),
                    token.toLowerCase()
                ),
                criteriaBuilder.greaterThanOrEqualTo(
                    tokens.<Date>get("validTo"),
                    new Date()
                )
        );
        List<ApiToken> results = EntityManagerHelper.entityManager().createQuery(criteriaQuery).getResultList();
        if (results == null) {
            return null;
        } //if
        if (results.isEmpty()) {
            return null;
        } //if
        return results.get(0);
    }

    public ApiToken generateApiToken(UserAccount userAccount) throws ApiException {
        if (userAccount == null) {
            throw new ApiException("Undefined user account...");
        } //if
        ApiToken apiToken = new ApiToken();
        apiToken.setCreated(new Date());
        apiToken.setModified(new Date());
        apiToken.setAccessToken(
                DigestUtils.sha256Hex(
                        RandomStringUtils.randomAlphanumeric(255)
                )
        );
        apiToken.setRefreshToken(
                DigestUtils.sha256Hex(
                        RandomStringUtils.randomAlphanumeric(255)
                )
        );
        apiToken.setUserAccount(userAccount);
        apiToken.setValidTo(
                DateUtils.addMinutes(new Date(), 30)
        );
        EntityManagerHelper.startTransaction();
        merge(apiToken);
        EntityManagerHelper.commitTransaction();
        return apiToken;
    }

    public boolean dropApiToken(UserAccount userAccount) throws ApiException {
        if (userAccount == null) {
            throw new ApiException("Undefined user account...");
        } //if

        EntityManagerHelper.startTransaction();

        userAccount.getApiTokens().forEach(this::delete);

        EntityManagerHelper.commitTransaction();

        return true;
    }
}
