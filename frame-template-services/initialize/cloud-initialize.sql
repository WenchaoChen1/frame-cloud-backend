INSERT INTO "public"."sys_tenant" ("id", "z_created_account", "z_created_date", "z_created_user", "z_updated_account", "z_updated_date", "z_updated_user", "parent_id", "description", "status", "tenant_code", "tenant_name", "type") VALUES ('840c291a-0314-492d-8e2d-4cccce9ddbd4', NULL, NULL, NULL, NULL, NULL, NULL, '0', NULL, 0, '0', '0', 0);

INSERT INTO "public"."sys_user" ("user_id", "z_created_account", "z_created_date", "z_created_user", "z_updated_account", "z_updated_date", "z_updated_user", "account_expire_at", "avatar", "credentials_expire_at", "description", "email", "first_name", "gender", "last_name", "nick_name", "password", "phone_number", "status", "type", "username") VALUES ('a683c203-5422-44c4-a892-26178173437c', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'devsuperadmin', NULL, NULL, NULL, NULL, '{bcrypt}$2a$10$vXLaLXJUV8gZuInk4VNXKOtlJY8ylnIYp.QURBGn6WhOil9qkRcue', '11111', 0, 0, 'devsuperadmin');
INSERT INTO "public"."sys_account" ("account_id", "z_created_account", "z_created_date", "z_created_user", "z_updated_account", "z_updated_date", "z_updated_user", "identity", "name", "status", "tenant_id", "type", "user_id") VALUES ('1104fe4f-dcb1-4b6f-94c6-6bbdfc900f8f', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'a', 0, '840c291a-0314-492d-8e2d-4cccce9ddbd4', 0, 'a683c203-5422-44c4-a892-26178173437c');


