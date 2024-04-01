INSERT INTO "public"."tenant" ("id", "created_at", "created_by", "updated_at", "updated_by", "parent_id",
                               "address_line1", "address_line2", "city", "country", "deleted", "description",
                               "email_address", "first_name", "last_name", "logo", "phone_number", "state", "status",
                               "tenant_code", "tenant_name", "type", "website", "zip_code")
VALUES ('391a5ac3-9608-4c2d-85ab-854adfd8b91a', '2022-12-24 04:11:25.995653', '1104fe4f-dcb1-4b6f-94c6-6bbdfc900f8f',
        NULL, '1104fe4f-dcb1-4b6f-94c6-6bbdfc900f8f', '1', '11', '11', '11', '11', 0, NULL, 'verityPayAdmin@verity.com',
        '1', '1', NULL, '1', '11', 1, '955d9a5b-da63-4eba-bd14-85ceb0577fb5', 'verity pay', NULL, NULL, '11');
INSERT INTO "public"."depart" ("id", "created_at", "created_by", "updated_at", "updated_by", "parent_id", "code",
                               "deleted", "description", "name", "short_name", "sort", "status", "tenant_id")
VALUES ('7e549ae0-327b-4f38-b389-4c471e159f3c', '2022-12-24 04:11:26.495669', '1104fe4f-dcb1-4b6f-94c6-6bbdfc900f8f',
        NULL, '1104fe4f-dcb1-4b6f-94c6-6bbdfc900f8f', '1', '955d9a5b-da63-4eba-bd14-85ceb0577fb5', 0, NULL,
        'verity pay', NULL, 1, NULL, '391a5ac3-9608-4c2d-85ab-854adfd8b91a');
INSERT INTO "public"."user" ("id", "created_at", "created_by", "updated_at", "updated_by", "activate_token", "avatar",
                             "deleted", "email", "first_name", "gender", "icon", "last_login_time", "last_name",
                             "mobile", "password", "username")
VALUES ('c8a0f974-90f4-470e-8fb8-d53603c59142', '2022-12-24 04:14:41.210872', NULL, NULL, NULL, NULL,
        'http://dummyimage.com/100x100', 0, 'verityadmin', NULL, 93, NULL, NULL, NULL, '13677152331',
        'hBddpnNz1FrvjPkM11HkJRDzDeU2Isg15vkTiSmYYeoz1mwF5jlpExPz93GlfS9daTHHA7sVsmz8TwQW932xCNl2YNkhG06caPAwdZjC8jaGoBPjMMw775qLl8xwY6sFAyXArkPFVvpX1IZ8XbLvoxbNtMjMlSEkR3wF6sZy4Cf2wOdL5l0c4dVwJdHdX3UmPl/bKBNR+ny2whYYce9u7doDFJJ+OcXYauhRvNWtSYunhJzeTExGk7n4yFgP0iwHLW72cDskUTLn+gBczZoRfjcoUuemNJjXDKYcgnSU2vzuJXMdQp0kfeEp6mZPA8JWZk5HT3MfNgALE7WOvbEYGw==',
        'verityadmin');
INSERT INTO "public"."account" ("id", "created_at", "created_by", "updated_at", "updated_by", "deleted", "identity",
                                "name", "tenant_id", "type", "user_id")
VALUES ('15e4bd69-d9ec-4c6c-acdf-ab81dd2ee577', '2022-12-24 04:14:41.23903', NULL, NULL, NULL, 0, NULL, NULL,
        '391a5ac3-9608-4c2d-85ab-854adfd8b91a', '1', 'c8a0f974-90f4-470e-8fb8-d53603c59142');
INSERT INTO "public"."tb001_user" ("tb001_id", "tb001_email", "tb001_password", "user_id", "tb001_user_name")
VALUES ('9cf5a8c8-7136-4a2a-a7dd-0a3256279c33', 'verityadmin',
        'hBddpnNz1FrvjPkM11HkJRDzDeU2Isg15vkTiSmYYeoz1mwF5jlpExPz93GlfS9daTHHA7sVsmz8TwQW932xCNl2YNkhG06caPAwdZjC8jaGoBPjMMw775qLl8xwY6sFAyXArkPFVvpX1IZ8XbLvoxbNtMjMlSEkR3wF6sZy4Cf2wOdL5l0c4dVwJdHdX3UmPl/bKBNR+ny2whYYce9u7doDFJJ+OcXYauhRvNWtSYunhJzeTExGk7n4yFgP0iwHLW72cDskUTLn+gBczZoRfjcoUuemNJjXDKYcgnSU2vzuJXMdQp0kfeEp6mZPA8JWZk5HT3MfNgALE7WOvbEYGw==',
        'c8a0f974-90f4-470e-8fb8-d53603c59142', 'verityadmin');
