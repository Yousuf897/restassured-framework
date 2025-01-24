package auth;

import utils.ConfigUtility;

import java.time.Instant;

public class TokenManager {

    private static String superVisorToken;
    private static String generalUserToken;

    private static Instant superVisorTokenGeneratedAt;
    private static Instant generalUserTokenGeneratedAt;

    private static final long TOKEN_EXPIRATION_MINUTES = ConfigUtility.getTokenExpirationMinutes();

    public static String getSuperVisorToken() {
        if ( superVisorToken == null || isTokenExpired(superVisorTokenGeneratedAt) ) {
            superVisorToken = TokenGenerator.getSupervisorToken();
            superVisorTokenGeneratedAt = Instant.now();
        }
        return superVisorToken;
    }

    public static String getGeneralUserToken() {
        if ( generalUserToken == null || isTokenExpired(generalUserTokenGeneratedAt) ) {
            generalUserToken = TokenGenerator.getSupervisorToken();
            generalUserTokenGeneratedAt = Instant.now();
        }
        return generalUserToken;
    }

    private static boolean isTokenExpired(Instant generateAt) {
        if ( generateAt == null ) return true;
       Instant expirationTime = generateAt.plusSeconds(TOKEN_EXPIRATION_MINUTES * 60);
       return Instant.now().isAfter(expirationTime);
    }

    public static void tearDown() {
        superVisorToken = null;
        generalUserToken = null;
        superVisorTokenGeneratedAt = null;
        generalUserTokenGeneratedAt = null;
    }

}
