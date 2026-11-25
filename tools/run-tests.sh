#!/bin/sh
set -eu

cd "$(CDPATH= cd "$(dirname "$0")/.." && pwd)"

usage() {
  printf "%s\n" "Usage: sh tools/run-tests.sh list | --allow-sut one CASE | --allow-sut group AREA | --allow-sut all" >&2
  exit 64
}
allow_sut=false
if [ "${1-}" = "--allow-sut" ]; then allow_sut=true; shift; fi
case "${1-}" in
  list)
    [ "$#" -eq 1 ] || usage
    printf '%s\n' 'login-success [login] Логин валидной парой'
    printf '%s\n' 'login-invalid-password [login] Логин с неверным паролем'
    printf '%s\n' 'login-empty-password [login] Логин с пустым паролем'
    printf '%s\n' 'login-nonexistent-username [login] Логин с несуществующим логином'
    printf '%s\n' 'login-empty-username [login] Логин с пустым логином'
    printf '%s\n' 'login-empty-credentials [login] Логин с пустыми логином и паролем'
    printf '%s\n' 'login-short-username [login] Логин короче минимальной длины'
    printf '%s\n' 'login-short-password [login] Пароль короче минимальной длины'
    exit 0 ;;
  one|group) [ "$#" -eq 2 ] || usage ;;
  all) [ "$#" -eq 1 ] || usage ;;
  *) usage ;;
esac

require_sut() {
  [ "$allow_sut" = true ] || {
    printf "%s\n" "BLOCKED: approve external SUT access and any selected registration writes before using --allow-sut." >&2
    exit 77
  }
}

case "$1:${2-}" in
  'one:login-success')
    require_sut
    printf '%s\n' '+ gradle --offline test --tests '\''tests.LoginTest.successfulLoginTest'\'''
    exec gradle --offline test --tests 'tests.LoginTest.successfulLoginTest'
    ;;
  'one:login-invalid-password')
    require_sut
    printf '%s\n' '+ gradle --offline test --tests '\''tests.LoginTest.unsuccessfulLoginWithInvalidPasswordTest'\'''
    exec gradle --offline test --tests 'tests.LoginTest.unsuccessfulLoginWithInvalidPasswordTest'
    ;;
  'one:login-empty-password')
    require_sut
    printf '%s\n' '+ gradle --offline test --tests '\''tests.LoginTest.unsuccessfulLoginWithEmptyPasswordTest'\'''
    exec gradle --offline test --tests 'tests.LoginTest.unsuccessfulLoginWithEmptyPasswordTest'
    ;;
  'one:login-nonexistent-username')
    require_sut
    printf '%s\n' '+ gradle --offline test --tests '\''tests.LoginTest.unsuccessfulLoginWithNonexistentUserNameTest'\'''
    exec gradle --offline test --tests 'tests.LoginTest.unsuccessfulLoginWithNonexistentUserNameTest'
    ;;
  'one:login-empty-username')
    require_sut
    printf '%s\n' '+ gradle --offline test --tests '\''tests.LoginTest.unsuccessfulLoginWithEmptyUserNameTest'\'''
    exec gradle --offline test --tests 'tests.LoginTest.unsuccessfulLoginWithEmptyUserNameTest'
    ;;
  'one:login-empty-credentials')
    require_sut
    printf '%s\n' '+ gradle --offline test --tests '\''tests.LoginTest.unsuccessfulLoginWithEmptyCredentialsTest'\'''
    exec gradle --offline test --tests 'tests.LoginTest.unsuccessfulLoginWithEmptyCredentialsTest'
    ;;
  'one:login-short-username')
    require_sut
    printf '%s\n' '+ gradle --offline test --tests '\''tests.LoginTest.unsuccessfulLoginWithShortUserNameTest'\'''
    exec gradle --offline test --tests 'tests.LoginTest.unsuccessfulLoginWithShortUserNameTest'
    ;;
  'one:login-short-password')
    require_sut
    printf '%s\n' '+ gradle --offline test --tests '\''tests.LoginTest.unsuccessfulLoginWithShortPasswordTest'\'''
    exec gradle --offline test --tests 'tests.LoginTest.unsuccessfulLoginWithShortPasswordTest'
    ;;
  'group:login')
    require_sut
    printf '%s\n' '+ gradle --offline test --tests '\''tests.LoginTest'\'''
    exec gradle --offline test --tests 'tests.LoginTest'
    ;;
  'all:')
    require_sut
    printf '%s\n' '+ gradle --offline test'
    exec gradle --offline test
    ;;
  *) usage ;;
esac
