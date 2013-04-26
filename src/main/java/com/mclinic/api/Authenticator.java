package com.mclinic.api;

import java.net.URLConnection;

/**
 */
public interface Authenticator {
    URLConnection authenticate(final URLConnection urlConnection);
}
