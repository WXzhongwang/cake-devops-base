package com.rany.cake.devops.base.service.ssh.auth;

import org.apache.sshd.server.auth.pubkey.PublickeyAuthenticator;


@FunctionalInterface
public interface PublicKeyAuthenticatorProvider extends PublickeyAuthenticator {

}
