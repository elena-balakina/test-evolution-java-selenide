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
    printf '%s\n' 'registration-success [registration] Успешная регистрация нового пользователя'
    printf '%s\n' 'registration-existing-username [registration] Повторная регистрация с тем же логином'
    printf '%s\n' 'registration-password-mismatch [registration] Несовпадающие пароль и подтверждение'
    printf '%s\n' 'registration-empty-password-confirmation [registration] Пустое подтверждение пароля'
    printf '%s\n' 'registration-empty-username [registration] Пустой логин'
    printf '%s\n' 'registration-short-username [registration] Логин короче минимальной длины'
    printf '%s\n' 'registration-short-password [registration] Пароль короче минимальной длины'
    printf '%s\n' 'registration-empty-credentials [registration] Пустые данные регистрации'
    printf '%s\n' 'logout-success [logout] Успешный выход из учётной записи'
    printf '%s\n' 'home-after-login [home] Главная страница после логина'
    printf '%s\n' 'home-without-login [home] Главная страница без логина'
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
  'one:registration-success')
    require_sut
    printf '%s\n' '+ gradle --offline test --tests '\''tests.RegistrationTest.successfulRegistrationTest'\'''
    exec gradle --offline test --tests 'tests.RegistrationTest.successfulRegistrationTest'
    ;;
  'one:registration-existing-username')
    require_sut
    printf '%s\n' '+ gradle --offline test --tests '\''tests.RegistrationTest.unsuccessfulRegistrationWithExistingUserNameTest'\'''
    exec gradle --offline test --tests 'tests.RegistrationTest.unsuccessfulRegistrationWithExistingUserNameTest'
    ;;
  'one:registration-password-mismatch')
    require_sut
    printf '%s\n' '+ gradle --offline test --tests '\''tests.RegistrationTest.unsuccessfulRegistrationWithDifferentPasswordsTest'\'''
    exec gradle --offline test --tests 'tests.RegistrationTest.unsuccessfulRegistrationWithDifferentPasswordsTest'
    ;;
  'one:registration-empty-password-confirmation')
    require_sut
    printf '%s\n' '+ gradle --offline test --tests '\''tests.RegistrationTest.unsuccessfulRegistrationWithEmptyPasswordConfirmationTest'\'''
    exec gradle --offline test --tests 'tests.RegistrationTest.unsuccessfulRegistrationWithEmptyPasswordConfirmationTest'
    ;;
  'one:registration-empty-username')
    require_sut
    printf '%s\n' '+ gradle --offline test --tests '\''tests.RegistrationTest.unsuccessfulRegistrationWithEmptyUserNameTest'\'''
    exec gradle --offline test --tests 'tests.RegistrationTest.unsuccessfulRegistrationWithEmptyUserNameTest'
    ;;
  'one:registration-short-username')
    require_sut
    printf '%s\n' '+ gradle --offline test --tests '\''tests.RegistrationTest.unsuccessfulRegistrationWithShortUserNameTest'\'''
    exec gradle --offline test --tests 'tests.RegistrationTest.unsuccessfulRegistrationWithShortUserNameTest'
    ;;
  'one:registration-short-password')
    require_sut
    printf '%s\n' '+ gradle --offline test --tests '\''tests.RegistrationTest.unsuccessfulRegistrationWithShortPasswordTest'\'''
    exec gradle --offline test --tests 'tests.RegistrationTest.unsuccessfulRegistrationWithShortPasswordTest'
    ;;
  'one:registration-empty-credentials')
    require_sut
    printf '%s\n' '+ gradle --offline test --tests '\''tests.RegistrationTest.unsuccessfulRegistrationWithEmptyCredentialsTest'\'''
    exec gradle --offline test --tests 'tests.RegistrationTest.unsuccessfulRegistrationWithEmptyCredentialsTest'
    ;;
  'one:logout-success')
    require_sut
    printf '%s\n' '+ gradle --offline test --tests '\''tests.LogoutTest.successfulLogoutTest'\'''
    exec gradle --offline test --tests 'tests.LogoutTest.successfulLogoutTest'
    ;;
  'one:home-after-login')
    require_sut
    printf '%s\n' '+ gradle --offline test --tests '\''tests.HomeTest.homePageIsDisplayedAfterLoginTest'\'''
    exec gradle --offline test --tests 'tests.HomeTest.homePageIsDisplayedAfterLoginTest'
    ;;
  'one:home-without-login')
    require_sut
    printf '%s\n' '+ gradle --offline test --tests '\''tests.HomeTest.homePageIsDisplayedWithoutLoginTest'\'''
    exec gradle --offline test --tests 'tests.HomeTest.homePageIsDisplayedWithoutLoginTest'
    ;;
  'group:login')
    require_sut
    printf '%s\n' '+ gradle --offline test --tests '\''tests.LoginTest'\'''
    exec gradle --offline test --tests 'tests.LoginTest'
    ;;
  'group:registration')
    require_sut
    printf '%s\n' '+ gradle --offline test --tests '\''tests.RegistrationTest'\'''
    exec gradle --offline test --tests 'tests.RegistrationTest'
    ;;
  'group:logout')
    require_sut
    printf '%s\n' '+ gradle --offline test --tests '\''tests.LogoutTest'\'''
    exec gradle --offline test --tests 'tests.LogoutTest'
    ;;
  'group:home')
    require_sut
    printf '%s\n' '+ gradle --offline test --tests '\''tests.HomeTest'\'''
    exec gradle --offline test --tests 'tests.HomeTest'
    ;;
  'all:')
    require_sut
    printf '%s\n' '+ gradle --offline test'
    exec gradle --offline test
    ;;
  *) usage ;;
esac
