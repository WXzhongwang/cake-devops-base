package com.rany.cake.devops.ssh.base.auth;

import org.apache.sshd.server.auth.pubkey.PublickeyAuthenticator;


@FunctionalInterface
public interface PublicKeyAuthenticatorProvider extends PublickeyAuthenticator {

}
