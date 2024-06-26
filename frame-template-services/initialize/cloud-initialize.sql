INSERT INTO "public"."sys_tenant" ("id", "z_created_account", "z_created_date", "z_created_user", "z_updated_account",
                                   "z_updated_date", "z_updated_user", "parent_id", "deleted", "description", "status",
                                   "tenant_code", "tenant_name", "type")
VALUES ('840c291a-0314-492d-8e2d-4cccce9ddbd4', NULL, '2024-06-23 10:11:29+00', NULL, NULL, '2024-06-23 10:11:33+00',
        NULL, '0', 0, NULL, 0, '0', '0', 0);

INSERT INTO "public"."sys_user" ("id", "z_created_account", "z_created_date", "z_created_user", "z_updated_account",
                                 "z_updated_date", "z_updated_user", "account_expire_at", "avatar",
                                 "credentials_expire_at", "email", "first_name", "gender", "last_name", "nick_name",
                                 "password", "phone_number", "status", "username")
VALUES ('a683c203-5422-44c4-a892-26178173437c', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'devsuperadmin',
        NULL, NULL, NULL, NULL, '{bcrypt}$2a$10$vXLaLXJUV8gZuInk4VNXKOtlJY8ylnIYp.QURBGn6WhOil9qkRcue', '11111', 0,
        'devsuperadmin');
INSERT INTO "public"."sys_account" ("id", "z_created_account", "z_created_date", "z_created_user", "z_updated_account",
                                    "z_updated_date", "z_updated_user", "deleted", "identity", "name", "status",
                                    "tenant_id", "type", "user_id")
VALUES ('1104fe4f-dcb1-4b6f-94c6-6bbdfc900f8f', NULL, NULL, NULL, NULL, NULL, NULL, 0, '0', '0', 0,
        '840c291a-0314-492d-8e2d-4cccce9ddbd4', 0, 'a683c203-5422-44c4-a892-26178173437c');

INSERT INTO "public"."sys_menu" ("id", "z_created_account", "z_created_date", "z_created_user", "z_updated_account",
                                 "z_updated_date", "z_updated_user", "parent_id", "code", "deleted", "description",
                                 "icon", "location", "menu_name", "name", "path", "sort", "status", "type",
                                 "tenant_enable")
VALUES ('15a15851-64ac-4405-bcf3-a68513d6e2a9', NULL, NULL, NULL, NULL, NULL, NULL,
        'b4907b4e-c2fd-40ab-8a19-d9b3f5aac62f', '100', 0, NULL, NULL, 'LEFT-MENU', '1', 'application',
        '/identity-management/application', 2, 1, 0, 0);
INSERT INTO "public"."sys_menu" ("id", "z_created_account", "z_created_date", "z_created_user", "z_updated_account",
                                 "z_updated_date", "z_updated_user", "parent_id", "code", "deleted", "description",
                                 "icon", "location", "menu_name", "name", "path", "sort", "status", "type",
                                 "tenant_enable")
VALUES ('2ca50de8-e095-41ca-8a9c-042e4c6278e4', NULL, NULL, NULL, NULL, NULL, NULL,
        'b4907b4e-c2fd-40ab-8a19-d9b3f5aac62f', '101', 0, NULL, NULL, 'LEFT-MENU', '1', 'compliance',
        '/identity-management/compliance', 3, 1, 0, 0);
INSERT INTO "public"."sys_menu" ("id", "z_created_account", "z_created_date", "z_created_user", "z_updated_account",
                                 "z_updated_date", "z_updated_user", "parent_id", "code", "deleted", "description",
                                 "icon", "location", "menu_name", "name", "path", "sort", "status", "type",
                                 "tenant_enable")
VALUES ('ac31a2bf-4269-4f49-81c9-155e36b7b2c3', NULL, NULL, NULL, NULL, NULL, NULL,
        'b4907b4e-c2fd-40ab-8a19-d9b3f5aac62f', '103', 0, NULL, NULL, 'LEFT-MENU', '1', 'scope.list',
        '/identity-management/scope-list', 4, 1, 0, 0);
INSERT INTO "public"."sys_menu" ("id", "z_created_account", "z_created_date", "z_created_user", "z_updated_account",
                                 "z_updated_date", "z_updated_user", "parent_id", "code", "deleted", "description",
                                 "icon", "location", "menu_name", "name", "path", "sort", "status", "type",
                                 "tenant_enable")
VALUES ('bec90fd2-9e40-4957-9f09-eb2c4add3f96', NULL, NULL, NULL, NULL, NULL, NULL,
        'b0b70f3e-1870-4d68-bcd2-a1f912ffc914', '202', 0, NULL, NULL, 'LEFT-MENU', '1', 'dict.dict-list',
        '/system-management/dict', 2, 1, 0, 0);
INSERT INTO "public"."sys_menu" ("id", "z_created_account", "z_created_date", "z_created_user", "z_updated_account",
                                 "z_updated_date", "z_updated_user", "parent_id", "code", "deleted", "description",
                                 "icon", "location", "menu_name", "name", "path", "sort", "status", "type",
                                 "tenant_enable")
VALUES ('372bca81-0f61-4d75-bb7a-d7ecec077ad5', NULL, NULL, NULL, NULL, NULL, NULL,
        'b0b70f3e-1870-4d68-bcd2-a1f912ffc914', '203', 0, NULL, NULL, 'LEFT-MENU', '1', 'tenant.tenant-list',
        '/system-management/tenant', 3, 1, 0, 0);
INSERT INTO "public"."sys_menu" ("id", "z_created_account", "z_created_date", "z_created_user", "z_updated_account",
                                 "z_updated_date", "z_updated_user", "parent_id", "code", "deleted", "description",
                                 "icon", "location", "menu_name", "name", "path", "sort", "status", "type",
                                 "tenant_enable")
VALUES ('a0b7bbff-93ba-4587-bdad-13d7d2bdd170', NULL, NULL, NULL, NULL, NULL, NULL,
        'b0b70f3e-1870-4d68-bcd2-a1f912ffc914', '204', 0, NULL, NULL, 'LEFT-MENU', '1', 'organize.organize-list',
        '/system-management/organize', 4, 1, 0, 0);
INSERT INTO "public"."sys_menu" ("id", "z_created_account", "z_created_date", "z_created_user", "z_updated_account",
                                 "z_updated_date", "z_updated_user", "parent_id", "code", "deleted", "description",
                                 "icon", "location", "menu_name", "name", "path", "sort", "status", "type",
                                 "tenant_enable")
VALUES ('4f654453-7dcc-470b-aa86-2a52d39d3c81', NULL, NULL, NULL, NULL, NULL, NULL,
        'b0b70f3e-1870-4d68-bcd2-a1f912ffc914', '205', 0, NULL, NULL, 'LEFT-MENU', '1', 'role.role-list',
        '/system-management/role', 5, 1, 0, 0);
INSERT INTO "public"."sys_menu" ("id", "z_created_account", "z_created_date", "z_created_user", "z_updated_account",
                                 "z_updated_date", "z_updated_user", "parent_id", "code", "deleted", "description",
                                 "icon", "location", "menu_name", "name", "path", "sort", "status", "type",
                                 "tenant_enable")
VALUES ('b65637db-8db1-44ec-822e-0612f0e84324', NULL, NULL, NULL, NULL, NULL, NULL,
        'b0b70f3e-1870-4d68-bcd2-a1f912ffc914', '206', 0, NULL, NULL, 'LEFT-MENU', '1', 'user.user-list',
        '/system-management/user-list', 6, 1, 0, 0);
INSERT INTO "public"."sys_menu" ("id", "z_created_account", "z_created_date", "z_created_user", "z_updated_account",
                                 "z_updated_date", "z_updated_user", "parent_id", "code", "deleted", "description",
                                 "icon", "location", "menu_name", "name", "path", "sort", "status", "type",
                                 "tenant_enable")
VALUES ('5ad13691-5289-4dd9-9696-5e624eb88262', NULL, NULL, NULL, NULL, NULL, NULL,
        'b0b70f3e-1870-4d68-bcd2-a1f912ffc914', '207', 0, NULL, NULL, 'LEFT-MENU', '1', 'account.account-list',
        '/system-management/account', 7, 1, 0, 0);
INSERT INTO "public"."sys_menu" ("id", "z_created_account", "z_created_date", "z_created_user", "z_updated_account",
                                 "z_updated_date", "z_updated_user", "parent_id", "code", "deleted", "description",
                                 "icon", "location", "menu_name", "name", "path", "sort", "status", "type",
                                 "tenant_enable")
VALUES ('5e43c0e0-dc2d-4316-86ae-31aabb392f7a', NULL, NULL, NULL, NULL, NULL, NULL,
        'b0b70f3e-1870-4d68-bcd2-a1f912ffc914', '208', 0, NULL, NULL, 'LEFT-MENU', '1', 'permission.permission-list',
        '/system-management/permission', 8, 1, 0, 0);
INSERT INTO "public"."sys_menu" ("id", "z_created_account", "z_created_date", "z_created_user", "z_updated_account",
                                 "z_updated_date", "z_updated_user", "parent_id", "code", "deleted", "description",
                                 "icon", "location", "menu_name", "name", "path", "sort", "status", "type",
                                 "tenant_enable")
VALUES ('b4907b4e-c2fd-40ab-8a19-d9b3f5aac62f', NULL, NULL, NULL, NULL, NULL, NULL, '0', 'identityManagement', 0, NULL,
        NULL, 'LEFT-MENU', '1', 'identity-management', '/identity-management', 1, 1, 0, 0);
INSERT INTO "public"."sys_menu" ("id", "z_created_account", "z_created_date", "z_created_user", "z_updated_account",
                                 "z_updated_date", "z_updated_user", "parent_id", "code", "deleted", "description",
                                 "icon", "location", "menu_name", "name", "path", "sort", "status", "type",
                                 "tenant_enable")
VALUES ('a2e37885-8632-49fa-acc6-7083bf652754', NULL, NULL, NULL, NULL, '2024-06-19 06:10:46.981213+00', NULL,
        'b0b70f3e-1870-4d68-bcd2-a1f912ffc914', '201', 0, NULL, NULL, 'LEFT-MENU', '1', 'menu.menu-list',
        '/system-management/menu-list', 1, 1, 0, 0);
INSERT INTO "public"."sys_menu" ("id", "z_created_account", "z_created_date", "z_created_user", "z_updated_account",
                                 "z_updated_date", "z_updated_user", "parent_id", "code", "deleted", "description",
                                 "icon", "location", "menu_name", "name", "path", "sort", "status", "type",
                                 "tenant_enable")
VALUES ('987f8793-8ea9-4b97-867b-01e43ab921aa', NULL, '2024-06-19 06:10:46.981213+00', NULL, NULL,
        '2024-06-19 06:10:46.981213+00', NULL, 'b4907b4e-c2fd-40ab-8a19-d9b3f5aac62f', '1', 0, NULL, NULL, 'LEFT-MENU',
        '1', 'authorization.list', '/identity-management/authorization-list', 1, 1, 0, 0);
INSERT INTO "public"."sys_menu" ("id", "z_created_account", "z_created_date", "z_created_user", "z_updated_account",
                                 "z_updated_date", "z_updated_user", "parent_id", "code", "deleted", "description",
                                 "icon", "location", "menu_name", "name", "path", "sort", "status", "type",
                                 "tenant_enable")
VALUES ('b0b70f3e-1870-4d68-bcd2-a1f912ffc914', NULL, NULL, NULL, NULL, '2024-06-20 02:54:45.434047+00', NULL, '0',
        'systemManagement', 0, '功能测试了', NULL, 'LEFT-MENU', '1', 'system-management', '/system-management', 1, 1, 0,
        0);
