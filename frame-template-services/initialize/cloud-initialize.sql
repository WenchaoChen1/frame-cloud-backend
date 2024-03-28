//项目启动初始化数据
//创建租户数据
  INSERT INTO "public"."tenant" ("id", "created_at", "created_by", "updated_at", "updated_by", "parent_id", "address_line1", "address_line2", "city", "country", "deleted", "description", "email_address", "first_name", "last_name", "logo", "phone_number", "state", "status", "tenant_code", "tenant_name", "type", "website", "zip_code") VALUES ('1', NULL, NULL, NULL, NULL, '0', '0', '0', '0', '0', 0, '0', '0', '0', '0', '', '0', '0', 0, '0', '0', 0, '0', '0');
//创建部门
  INSERT INTO "public"."depart" ("id", "created_at", "created_by", "updated_at", "updated_by", "parent_id", "code", "deleted", "description", "name", "short_name", "sort", "status", "tenant_id") VALUES ('1', NULL, NULL, NULL, NULL, '0', '0', 0, '0', '0', '0', 0, 0, '1');
//创建用户,账户,identity数据  devsuperadmin   Black.123!@#
  INSERT INTO "public"."user" ("id", "created_at", "created_by", "updated_at", "updated_by", "activate_token", "avatar", "deleted", "email", "first_name", "gender", "icon", "last_login_time", "last_name", "mobile", "password", "username") VALUES ('a683c203-5422-44c4-a892-26178173437c', '2022-12-24 02:50:16.627916', NULL, NULL, NULL, NULL, 'http://dummyimage.com/100x100', 0, 'devsuperadmin', NULL, 93, NULL, NULL, NULL, '13677152331', 'QdftBPCZIZklXITEPzoBeMITjjpwc+n7RaJkE0sH/aIkAroPFBBdRSa5liX7HIbQZWOKH+baxBOeK8JvFBLR0RaWHI23k5bULR6Iy/rDeieEgelmURom5iLTgIFlKYDQOCFfcUsbLckpTsROB+tl3wqRH7oET9oXLhSFPevZp97tueOV/t5xF/34HaSjS/90qpH75uFa3O0ifX72UGBriisSn9fCN9rr2FMA7fHxZ9T2I+5c7UGoIpM9bicvSvJKjiTTFIutqHz/15hobjNvMam99+SMZPgclPcJvUNkKHLpSOcCPQLAe4kScXUNdU8z0tllHbOf6bvC8/c0jAT/NQ==', 'devsuperadmin');
  INSERT INTO "public"."account" ("id", "created_at", "created_by", "updated_at", "updated_by", "deleted", "identity", "name", "tenant_id", "type", "user_id") VALUES ('1104fe4f-dcb1-4b6f-94c6-6bbdfc900f8f', '2022-12-24 02:50:16.802927', NULL, NULL, NULL, 0, NULL, NULL, '1', '0', 'a683c203-5422-44c4-a892-26178173437c');
  INSERT INTO "public"."tb001_user" ("tb001_id", "tb001_email", "tb001_password", "user_id", "tb001_user_name") VALUES ('312fb93d-c596-4947-801d-dc84bafd258f', 'devsuperadmin', 'QdftBPCZIZklXITEPzoBeMITjjpwc+n7RaJkE0sH/aIkAroPFBBdRSa5liX7HIbQZWOKH+baxBOeK8JvFBLR0RaWHI23k5bULR6Iy/rDeieEgelmURom5iLTgIFlKYDQOCFfcUsbLckpTsROB+tl3wqRH7oET9oXLhSFPevZp97tueOV/t5xF/34HaSjS/90qpH75uFa3O0ifX72UGBriisSn9fCN9rr2FMA7fHxZ9T2I+5c7UGoIpM9bicvSvJKjiTTFIutqHz/15hobjNvMam99+SMZPgclPcJvUNkKHLpSOcCPQLAe4kScXUNdU8z0tllHbOf6bvC8/c0jAT/NQ==', 'a683c203-5422-44c4-a892-26178173437c', 'devsuperadmin');


