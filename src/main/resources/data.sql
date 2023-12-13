INSERT INTO roles (id, name)
VALUES (1, 'ROLE_USER'), (2, 'ROLE_MODERATOR')
ON CONFLICT (id) DO NOTHING;

INSERT INTO categories (id, name, parent_id)
VALUES (1, 'Дом (Квартира)', 0)
ON CONFLICT (id) DO UPDATE SET name='Дом (Квартира)', parent_id=0;

INSERT INTO categories (id, name, parent_id)
VALUES (2, 'Аварийность', 1)
ON CONFLICT (id) DO UPDATE SET name='Аварийность', parent_id=1;

INSERT INTO categories (id, name, parent_id)
VALUES (3, 'Аварийность', 2)
ON CONFLICT (id) DO UPDATE SET name='Аварийность', parent_id=2;

INSERT INTO categories (id, name, parent_id)
VALUES (5, 'Благоустройство', 1)
ON CONFLICT (id) DO UPDATE SET name='Благоустройство', parent_id=1;

INSERT INTO categories (id, name, parent_id)
VALUES (6, 'Несанкционированные надписи / объявления на стенах дома', 5)
ON CONFLICT (id) DO UPDATE SET name='Несанкционированные надписи / объявления на стенах дома', parent_id=5;

INSERT INTO categories (id, name, parent_id)
VALUES (8, 'Водоотведение', 1)
ON CONFLICT (id) DO UPDATE SET name='Водоотведение', parent_id=1;

INSERT INTO categories (id, name, parent_id)
VALUES (9, 'Засор канализации/протечка канализационной трубы', 8)
ON CONFLICT (id) DO UPDATE SET name='Засор канализации/протечка канализационной трубы', parent_id=8;

INSERT INTO categories (id, name, parent_id)
VALUES (11, 'Водоснабжение', 1)
ON CONFLICT (id) DO UPDATE SET name='Водоснабжение', parent_id=1;

INSERT INTO categories (id, name, parent_id)
VALUES (12, 'Ненадлежащее содержание трубопроводов и элементов системы водоснабжения', 11)
ON CONFLICT (id) DO UPDATE SET name='Ненадлежащее содержание трубопроводов и элементов системы водоснабжения', parent_id=11;

INSERT INTO categories (id, name, parent_id)
VALUES (14, 'Несанкционированный водозабор холодной, горячей воды из общедомовых сетей', 11)
ON CONFLICT (id) DO UPDATE SET name='Несанкционированный водозабор холодной, горячей воды из общедомовых сетей', parent_id=11;

INSERT INTO categories (id, name, parent_id)
VALUES (16, 'Протечка труб в подвале, на чердаке, на лестничной площадке', 11)
ON CONFLICT (id) DO UPDATE SET name='Протечка труб в подвале, на чердаке, на лестничной площадке', parent_id=11;

INSERT INTO categories (id, name, parent_id)
VALUES (18, 'Отсутствие горячей воды', 11)
ON CONFLICT (id) DO UPDATE SET name='Отсутствие горячей воды', parent_id=11;

INSERT INTO categories (id, name, parent_id)
VALUES (20, 'Отсутствие холодной воды', 11)
ON CONFLICT (id) DO UPDATE SET name='Отсутствие холодной воды', parent_id=11;

INSERT INTO categories (id, name, parent_id)
VALUES (22, 'Плохое качество горячей воды', 11)
ON CONFLICT (id) DO UPDATE SET name='Плохое качество горячей воды', parent_id=11;

INSERT INTO categories (id, name, parent_id)
VALUES (24, 'Плохое качество холодной воды', 11)
ON CONFLICT (id) DO UPDATE SET name='Плохое качество холодной воды', parent_id=11;

INSERT INTO categories (id, name, parent_id)
VALUES (26, 'Слабый напор горячей воды', 11)
ON CONFLICT (id) DO UPDATE SET name='Слабый напор горячей воды', parent_id=11;

INSERT INTO categories (id, name, parent_id)
VALUES (28, 'Слабый напор холодной воды', 11)
ON CONFLICT (id) DO UPDATE SET name='Слабый напор холодной воды', parent_id=11;

INSERT INTO categories (id, name, parent_id)
VALUES (30, 'Капитальный ремонт', 1)
ON CONFLICT (id) DO UPDATE SET name='Капитальный ремонт', parent_id=1;

INSERT INTO categories (id, name, parent_id)
VALUES (31, 'Длительный срок производства работ', 30)
ON CONFLICT (id) DO UPDATE SET name='Длительный срок производства работ', parent_id=30;

INSERT INTO categories (id, name, parent_id)
VALUES (33, 'Наличие дефектов (недостатков) в выполненных работах', 30)
ON CONFLICT (id) DO UPDATE SET name='Наличие дефектов (недостатков) в выполненных работах', parent_id=30;

INSERT INTO categories (id, name, parent_id)
VALUES (35, 'Работы на объекте не осуществляются в соответствии с запланированными сроками', 30)
ON CONFLICT (id) DO UPDATE SET name='Работы на объекте не осуществляются в соответствии с запланированными сроками', parent_id=30;

INSERT INTO categories (id, name, parent_id)
VALUES (37, 'Кровля', 1)
ON CONFLICT (id) DO UPDATE SET name='Кровля', parent_id=1;

INSERT INTO categories (id, name, parent_id)
VALUES (38, 'Наличие наледи на крыше', 37)
ON CONFLICT (id) DO UPDATE SET name='Наличие наледи на крыше', parent_id=37;

INSERT INTO categories (id, name, parent_id)
VALUES (40, 'Неубранный снег', 37)
ON CONFLICT (id) DO UPDATE SET name='Неубранный снег', parent_id=37;

INSERT INTO categories (id, name, parent_id)
VALUES (42, 'Повреждение водосточной трубы', 37)
ON CONFLICT (id) DO UPDATE SET name='Повреждение водосточной трубы', parent_id=37;

INSERT INTO categories (id, name, parent_id)
VALUES (44, 'Повреждение кровли', 37)
ON CONFLICT (id) DO UPDATE SET name='Повреждение кровли', parent_id=37;

INSERT INTO categories (id, name, parent_id)
VALUES (46, 'Повреждение/засор внутреннего ливнестока', 37)
ON CONFLICT (id) DO UPDATE SET name='Повреждение/засор внутреннего ливнестока', parent_id=37;

INSERT INTO categories (id, name, parent_id)
VALUES (48, 'Нарушение правил пользования', 1)
ON CONFLICT (id) DO UPDATE SET name='Нарушение правил пользования', parent_id=1;

INSERT INTO categories (id, name, parent_id)
VALUES (49, 'Незаконное использование чердаков и подвалов', 48)
ON CONFLICT (id) DO UPDATE SET name='Незаконное использование чердаков и подвалов', parent_id=48;

INSERT INTO categories (id, name, parent_id)
VALUES (51, 'Незаконное проживание граждан в местах общего пользования многоквартирного дома (подвалы, чердаки) ', 48)
ON CONFLICT (id) DO UPDATE SET name='Незаконное проживание граждан в местах общего пользования многоквартирного дома (подвалы, чердаки) ', parent_id=48;

INSERT INTO categories (id, name, parent_id)
VALUES (53, 'Самовольное размещение наружн. блоков систем кондиционеров, вентиляции, вентиляционных трубопроводов, вентил. решеток, декоративных решеток, роллет, жалюзи, антенн, светильников, камер видеонабл., маркиз, флагодерж., громкоговор. на фасаде и кровле МКД', 48)
ON CONFLICT (id) DO UPDATE SET name='Самовольное размещение наружн. блоков систем кондиционеров, вентиляции, вентиляционных трубопроводов, вентил. решеток, декоративных решеток, роллет, жалюзи, антенн, светильников, камер видеонабл., маркиз, флагодерж., громкоговор. на фасаде и кровле МКД', parent_id=48;

INSERT INTO categories (id, name, parent_id)
VALUES (55, 'Организационно-правовые вопросы', 1)
ON CONFLICT (id) DO UPDATE SET name='Организационно-правовые вопросы', parent_id=1;

INSERT INTO categories (id, name, parent_id)
VALUES (56, 'В доме нет информации об управляющей компании', 55)
ON CONFLICT (id) DO UPDATE SET name='В доме нет информации об управляющей компании', parent_id=55;

INSERT INTO categories (id, name, parent_id)
VALUES (58, 'Нарушения при выборе / смене управляющей организации', 55)
ON CONFLICT (id) DO UPDATE SET name='Нарушения при выборе / смене управляющей организации', parent_id=55;

INSERT INTO categories (id, name, parent_id)
VALUES (60, 'Нарушения при создании ТСЖ', 55)
ON CONFLICT (id) DO UPDATE SET name='Нарушения при создании ТСЖ', parent_id=55;

INSERT INTO categories (id, name, parent_id)
VALUES (62, 'Начисление платы за коммунальные услуги по тарифам, отличающимся от установленных Комитетом по тарифам Санкт-Петербурга', 55)
ON CONFLICT (id) DO UPDATE SET name='Начисление платы за коммунальные услуги по тарифам, отличающимся от установленных Комитетом по тарифам Санкт-Петербурга', parent_id=55;

INSERT INTO categories (id, name, parent_id)
VALUES (64, 'Несогласие с выставленным счетом', 55)
ON CONFLICT (id) DO UPDATE SET name='Несогласие с выставленным счетом', parent_id=55;

INSERT INTO categories (id, name, parent_id)
VALUES (66, 'Отказ в составлении акта обследования или несоответствие акта действительности', 55)
ON CONFLICT (id) DO UPDATE SET name='Отказ в составлении акта обследования или несоответствие акта действительности', parent_id=55;

INSERT INTO categories (id, name, parent_id)
VALUES (68, 'Отсутствие информации об отключениях в связи с проведением ремонтных работ (на информационном стенде управляющей организации)', 55)
ON CONFLICT (id) DO UPDATE SET name='Отсутствие информации об отключениях в связи с проведением ремонтных работ (на информационном стенде управляющей организации)', parent_id=55;

INSERT INTO categories (id, name, parent_id)
VALUES (70, 'Подвал / Чердак', 1)
ON CONFLICT (id) DO UPDATE SET name='Подвал / Чердак', parent_id=1;

INSERT INTO categories (id, name, parent_id)
VALUES (71, 'Неисправен козырек над входом в подвал', 70)
ON CONFLICT (id) DO UPDATE SET name='Неисправен козырек над входом в подвал', parent_id=70;

INSERT INTO categories (id, name, parent_id)
VALUES (73, 'Открыты/закрыты подвальные окна, продухи, вход в подвал', 70)
ON CONFLICT (id) DO UPDATE SET name='Открыты/закрыты подвальные окна, продухи, вход в подвал', parent_id=70;

INSERT INTO categories (id, name, parent_id)
VALUES (75, 'Неудовлетворительное содержание чердачного помещения, принадлежащего собственникам помещений в многоквартирном доме на праве общей долевой собственности', 70)
ON CONFLICT (id) DO UPDATE SET name='Неудовлетворительное содержание чердачного помещения, принадлежащего собственникам помещений в многоквартирном доме на праве общей долевой собственности', parent_id=70;

INSERT INTO categories (id, name, parent_id)
VALUES (77, 'Санитарные состояние', 1)
ON CONFLICT (id) DO UPDATE SET name='Санитарные состояние', parent_id=1;

INSERT INTO categories (id, name, parent_id)
VALUES (78, 'Засор мусоропровода', 77)
ON CONFLICT (id) DO UPDATE SET name='Засор мусоропровода', parent_id=77;

INSERT INTO categories (id, name, parent_id)
VALUES (80, 'Требуется дезинсекция (насекомые), дезинфекция в местах общего пользования (в т.ч. мусоропровода)', 77)
ON CONFLICT (id) DO UPDATE SET name='Требуется дезинсекция (насекомые), дезинфекция в местах общего пользования (в т.ч. мусоропровода)', parent_id=77;

INSERT INTO categories (id, name, parent_id)
VALUES (82, 'Требуется дератизация (крысы) в местах общего пользования', 77)
ON CONFLICT (id) DO UPDATE SET name='Требуется дератизация (крысы) в местах общего пользования', parent_id=77;

INSERT INTO categories (id, name, parent_id)
VALUES (84, 'Содержание помещений ', 1)
ON CONFLICT (id) DO UPDATE SET name='Содержание помещений ', parent_id=1;

INSERT INTO categories (id, name, parent_id)
VALUES (85, 'Неисправна подъемная платформа для инвалидов', 84)
ON CONFLICT (id) DO UPDATE SET name='Неисправна подъемная платформа для инвалидов', parent_id=84;

INSERT INTO categories (id, name, parent_id)
VALUES (87, 'Неисправное освещение в лифте', 84)
ON CONFLICT (id) DO UPDATE SET name='Неисправное освещение в лифте', parent_id=84;

INSERT INTO categories (id, name, parent_id)
VALUES (89, 'Неисправное освещение в подъезде / на фасаде жилого здания', 84)
ON CONFLICT (id) DO UPDATE SET name='Неисправное освещение в подъезде / на фасаде жилого здания', parent_id=84;

INSERT INTO categories (id, name, parent_id)
VALUES (91, 'Неисправный (ая) пандус / аппарель', 84)
ON CONFLICT (id) DO UPDATE SET name='Неисправный (ая) пандус / аппарель', parent_id=84;

INSERT INTO categories (id, name, parent_id)
VALUES (93, 'Неисправный доводчик входной двери', 84)
ON CONFLICT (id) DO UPDATE SET name='Неисправный доводчик входной двери', parent_id=84;

INSERT INTO categories (id, name, parent_id)
VALUES (95, 'Неисправный домофон', 84)
ON CONFLICT (id) DO UPDATE SET name='Неисправный домофон', parent_id=84;

INSERT INTO categories (id, name, parent_id)
VALUES (97, 'Неисправный лифт', 84)
ON CONFLICT (id) DO UPDATE SET name='Неисправный лифт', parent_id=84;

INSERT INTO categories (id, name, parent_id)
VALUES (99, 'Неудовлетворительное состояние парадной', 84)
ON CONFLICT (id) DO UPDATE SET name='Неудовлетворительное состояние парадной', parent_id=84;

INSERT INTO categories (id, name, parent_id)
VALUES (101, 'Отсутствие или неисправность замка мусоросборной камеры', 84)
ON CONFLICT (id) DO UPDATE SET name='Отсутствие или неисправность замка мусоросборной камеры', parent_id=84;

INSERT INTO categories (id, name, parent_id)
VALUES (103, 'Отсутствие или несоблюдение графика уборки подъезда', 84)
ON CONFLICT (id) DO UPDATE SET name='Отсутствие или несоблюдение графика уборки подъезда', parent_id=84;

INSERT INTO categories (id, name, parent_id)
VALUES (105, 'Отсутствие/неисправность крышки загрузочного люка мусоропровода', 84)
ON CONFLICT (id) DO UPDATE SET name='Отсутствие/неисправность крышки загрузочного люка мусоропровода', parent_id=84;

INSERT INTO categories (id, name, parent_id)
VALUES (107, 'Разбиты стекла на лестничной площадке', 84)
ON CONFLICT (id) DO UPDATE SET name='Разбиты стекла на лестничной площадке', parent_id=84;

INSERT INTO categories (id, name, parent_id)
VALUES (109, 'Сломаны почтовые шкафы', 84)
ON CONFLICT (id) DO UPDATE SET name='Сломаны почтовые шкафы', parent_id=84;

INSERT INTO categories (id, name, parent_id)
VALUES (111, 'Повреждение отопительного прибора (радиатора и др.), запорной арматуры (вентиль, кран) протечка трубопровода системы центрального отопления на лестничной площадке / подвале / чердаке', 84)
ON CONFLICT (id) DO UPDATE SET name='Повреждение отопительного прибора (радиатора и др.), запорной арматуры (вентиль, кран) протечка трубопровода системы центрального отопления на лестничной площадке / подвале / чердаке', parent_id=84;

INSERT INTO categories (id, name, parent_id)
VALUES (113, 'Фасад', 1)
ON CONFLICT (id) DO UPDATE SET name='Фасад', parent_id=1;

INSERT INTO categories (id, name, parent_id)
VALUES (114, 'Герметизация стыков стеновых панелей', 113)
ON CONFLICT (id) DO UPDATE SET name='Герметизация стыков стеновых панелей', parent_id=113;

INSERT INTO categories (id, name, parent_id)
VALUES (116, 'Неудовлетворительное состояние архитектурных элементов фасада (архитектурные изыски)', 113)
ON CONFLICT (id) DO UPDATE SET name='Неудовлетворительное состояние архитектурных элементов фасада (архитектурные изыски)', parent_id=113;

INSERT INTO categories (id, name, parent_id)
VALUES (118, 'Неудовлетворительное состояние окраски фасада дома (кроме несанкционированных надписей / объявлений на стенах дома)', 113)
ON CONFLICT (id) DO UPDATE SET name='Неудовлетворительное состояние окраски фасада дома (кроме несанкционированных надписей / объявлений на стенах дома)', parent_id=113;

INSERT INTO categories (id, name, parent_id)
VALUES (120, 'Отсутствие адресной вывески с названием улицы и номером дома', 113)
ON CONFLICT (id) DO UPDATE SET name='Отсутствие адресной вывески с названием улицы и номером дома', parent_id=113;

INSERT INTO categories (id, name, parent_id)
VALUES (122, 'Повреждение участков цоколя, отмостки, приямков, вентиляционных продухов', 113)
ON CONFLICT (id) DO UPDATE SET name='Повреждение участков цоколя, отмостки, приямков, вентиляционных продухов', parent_id=113;

INSERT INTO categories (id, name, parent_id)
VALUES (124, 'Шум', 1)
ON CONFLICT (id) DO UPDATE SET name='Шум', parent_id=1;

INSERT INTO categories (id, name, parent_id)
VALUES (125, 'Требуется регулировка системы водоснабжения/отопления', 124)
ON CONFLICT (id) DO UPDATE SET name='Требуется регулировка системы водоснабжения/отопления', parent_id=124;

INSERT INTO categories (id, name, parent_id)
VALUES (127, 'Шум от инженерных сетей и оборудования', 124)
ON CONFLICT (id) DO UPDATE SET name='Шум от инженерных сетей и оборудования', parent_id=124;

INSERT INTO categories (id, name, parent_id)
VALUES (129, 'Электроснабжение', 1)
ON CONFLICT (id) DO UPDATE SET name='Электроснабжение', parent_id=1;

INSERT INTO categories (id, name, parent_id)
VALUES (130, 'Оголенные провода', 129)
ON CONFLICT (id) DO UPDATE SET name='Оголенные провода', parent_id=129;

INSERT INTO categories (id, name, parent_id)
VALUES (132, 'Неисправно освещение', 129)
ON CONFLICT (id) DO UPDATE SET name='Неисправно освещение', parent_id=129;

INSERT INTO categories (id, name, parent_id)
VALUES (134, 'Неисправно электроснабжение', 129)
ON CONFLICT (id) DO UPDATE SET name='Неисправно электроснабжение', parent_id=129;

INSERT INTO categories (id, name, parent_id)
VALUES (136, 'Вентиляция', 1)
ON CONFLICT (id) DO UPDATE SET name='Вентиляция', parent_id=1;

INSERT INTO categories (id, name, parent_id)
VALUES (137, 'Неисправность системы вентиляции', 136)
ON CONFLICT (id) DO UPDATE SET name='Неисправность системы вентиляции', parent_id=136;

INSERT INTO categories (id, name, parent_id)
VALUES (139, 'Центральное отопление', 1)
ON CONFLICT (id) DO UPDATE SET name='Центральное отопление', parent_id=1;

INSERT INTO categories (id, name, parent_id)
VALUES (140, 'Низкая температура в отапливаемом помещении', 139)
ON CONFLICT (id) DO UPDATE SET name='Низкая температура в отапливаемом помещении', parent_id=139;

INSERT INTO categories (id, name, parent_id)
VALUES (142, 'Повреждение запорной арматуры (вентиль, кран), стояка центрального отопления', 139)
ON CONFLICT (id) DO UPDATE SET name='Повреждение запорной арматуры (вентиль, кран), стояка центрального отопления', parent_id=139;

INSERT INTO categories (id, name, parent_id)
VALUES (144, 'Температура в помещении выше нормы', 139)
ON CONFLICT (id) DO UPDATE SET name='Температура в помещении выше нормы', parent_id=139;

INSERT INTO categories (id, name, parent_id)
VALUES (146, 'Двор', 0)
ON CONFLICT (id) DO UPDATE SET name='Двор', parent_id=0;

INSERT INTO categories (id, name, parent_id)
VALUES (147, 'Благоустройство', 146)
ON CONFLICT (id) DO UPDATE SET name='Благоустройство', parent_id=146;

INSERT INTO categories (id, name, parent_id)
VALUES (148, 'Конструкция, препятствующая парковке', 147)
ON CONFLICT (id) DO UPDATE SET name='Конструкция, препятствующая парковке', parent_id=147;

INSERT INTO categories (id, name, parent_id)
VALUES (150, 'Лужи на твердом покрытии', 147)
ON CONFLICT (id) DO UPDATE SET name='Лужи на твердом покрытии', parent_id=147;

INSERT INTO categories (id, name, parent_id)
VALUES (152, 'Мусор на внутридворовой территории', 147)
ON CONFLICT (id) DO UPDATE SET name='Мусор на внутридворовой территории', parent_id=147;

INSERT INTO categories (id, name, parent_id)
VALUES (154, 'Мусор на детской площадке', 147)
ON CONFLICT (id) DO UPDATE SET name='Мусор на детской площадке', parent_id=147;

INSERT INTO categories (id, name, parent_id)
VALUES (156, 'Мусор на спортивных площадках', 147)
ON CONFLICT (id) DO UPDATE SET name='Мусор на спортивных площадках', parent_id=147;

INSERT INTO categories (id, name, parent_id)
VALUES (158, 'Не оборудована контейнерная площадка', 147)
ON CONFLICT (id) DO UPDATE SET name='Не оборудована контейнерная площадка', parent_id=147;

INSERT INTO categories (id, name, parent_id)
VALUES (160, 'Не обустроена территория детской площадки', 147)
ON CONFLICT (id) DO UPDATE SET name='Не обустроена территория детской площадки', parent_id=147;

INSERT INTO categories (id, name, parent_id)
VALUES (162, 'Не обустроена территория спортивной площадки', 147)
ON CONFLICT (id) DO UPDATE SET name='Не обустроена территория спортивной площадки', parent_id=147;

INSERT INTO categories (id, name, parent_id)
VALUES (164, 'Неисправно освещение на фасаде здания', 147)
ON CONFLICT (id) DO UPDATE SET name='Неисправно освещение на фасаде здания', parent_id=147;

INSERT INTO categories (id, name, parent_id)
VALUES (166, 'Ненадлежащее состояние малых архитектурных форм, уличной мебели и хозяйственно-бытового оборудования, необходимого для благоустройства территории', 147)
ON CONFLICT (id) DO UPDATE SET name='Ненадлежащее состояние малых архитектурных форм, уличной мебели и хозяйственно-бытового оборудования, необходимого для благоустройства территории', parent_id=147;

INSERT INTO categories (id, name, parent_id)
VALUES (168, 'Ненадлежащее состояние ограждений газонов', 147)
ON CONFLICT (id) DO UPDATE SET name='Ненадлежащее состояние ограждений газонов', parent_id=147;

INSERT INTO categories (id, name, parent_id)
VALUES (170, 'Необходима прочистка ливневой канализации', 147)
ON CONFLICT (id) DO UPDATE SET name='Необходима прочистка ливневой канализации', parent_id=147;

INSERT INTO categories (id, name, parent_id)
VALUES (172, 'Неочищенные урны', 147)
ON CONFLICT (id) DO UPDATE SET name='Неочищенные урны', parent_id=147;

INSERT INTO categories (id, name, parent_id)
VALUES (174, 'Неубранный снег, требуется обработка территории пескосоляной смесью', 147)
ON CONFLICT (id) DO UPDATE SET name='Неубранный снег, требуется обработка территории пескосоляной смесью', parent_id=147;

INSERT INTO categories (id, name, parent_id)
VALUES (176, 'Неудовлетворительное содержание газонов (нескошенная/не убранная трава)', 147)
ON CONFLICT (id) DO UPDATE SET name='Неудовлетворительное содержание газонов (нескошенная/не убранная трава)', parent_id=147;

INSERT INTO categories (id, name, parent_id)
VALUES (178, 'Неудовлетворительное состояние асфальтового покрытия на придомовой и дворовой территориях', 147)
ON CONFLICT (id) DO UPDATE SET name='Неудовлетворительное состояние асфальтового покрытия на придомовой и дворовой территориях', parent_id=147;

INSERT INTO categories (id, name, parent_id)
VALUES (180, 'Неухоженный внешний вид деревьев и кустарников', 147)
ON CONFLICT (id) DO UPDATE SET name='Неухоженный внешний вид деревьев и кустарников', parent_id=147;

INSERT INTO categories (id, name, parent_id)
VALUES (182, 'Отсутствие зон отдыха', 147)
ON CONFLICT (id) DO UPDATE SET name='Отсутствие зон отдыха', parent_id=147;

INSERT INTO categories (id, name, parent_id)
VALUES (184, 'Предложения по устранению искусственных неровностей на проездах и въездах', 147)
ON CONFLICT (id) DO UPDATE SET name='Предложения по устранению искусственных неровностей на проездах и въездах', parent_id=147;

INSERT INTO categories (id, name, parent_id)
VALUES (186, 'Предложения по установке малых архитектурных форм, уличной мебели и хозяйственно-бытового оборудования, необходимого для благоустройства территории', 147)
ON CONFLICT (id) DO UPDATE SET name='Предложения по установке малых архитектурных форм, уличной мебели и хозяйственно-бытового оборудования, необходимого для благоустройства территории', parent_id=147;

INSERT INTO categories (id, name, parent_id)
VALUES (188, 'Провисающие, оборванные провода на зданиях', 147)
ON CONFLICT (id) DO UPDATE SET name='Провисающие, оборванные провода на зданиях', parent_id=147;

INSERT INTO categories (id, name, parent_id)
VALUES (190, 'Упавшее дерево', 147)
ON CONFLICT (id) DO UPDATE SET name='Упавшее дерево', parent_id=147;

INSERT INTO categories (id, name, parent_id)
VALUES (192, 'Утратившие жизнеспособность деревья и кустарники, угроза падения деревьев', 147)
ON CONFLICT (id) DO UPDATE SET name='Утратившие жизнеспособность деревья и кустарники, угроза падения деревьев', parent_id=147;

INSERT INTO categories (id, name, parent_id)
VALUES (194, 'Санитарное состояние', 146)
ON CONFLICT (id) DO UPDATE SET name='Санитарное состояние', parent_id=146;

INSERT INTO categories (id, name, parent_id)
VALUES (195, 'Неудовлетворительное санитарное и техническое состояние контейнерной площадки', 194)
ON CONFLICT (id) DO UPDATE SET name='Неудовлетворительное санитарное и техническое состояние контейнерной площадки', parent_id=194;

INSERT INTO categories (id, name, parent_id)
VALUES (197, 'Сломанный мусорный контейнер', 194)
ON CONFLICT (id) DO UPDATE SET name='Сломанный мусорный контейнер', parent_id=194;

INSERT INTO categories (id, name, parent_id)
VALUES (199, 'Требуется дезинсекция (насекомые), дезинфекция в местах общего пользования (в т.ч. мусоропровода)', 194)
ON CONFLICT (id) DO UPDATE SET name='Требуется дезинсекция (насекомые), дезинфекция в местах общего пользования (в т.ч. мусоропровода)', parent_id=194;

INSERT INTO categories (id, name, parent_id)
VALUES (201, 'Требуется дератизация (крысы) в местах общего пользования', 194)
ON CONFLICT (id) DO UPDATE SET name='Требуется дератизация (крысы) в местах общего пользования', parent_id=194;

INSERT INTO categories (id, name, parent_id)
VALUES (203, 'Улица', 0)
ON CONFLICT (id) DO UPDATE SET name='Улица', parent_id=0;

INSERT INTO categories (id, name, parent_id)
VALUES (204, 'Благоустройство', 203)
ON CONFLICT (id) DO UPDATE SET name='Благоустройство', parent_id=203;

INSERT INTO categories (id, name, parent_id)
VALUES (205, 'Деревья и кустарники имеют неухоженный вид', 204)
ON CONFLICT (id) DO UPDATE SET name='Деревья и кустарники имеют неухоженный вид', parent_id=204;

INSERT INTO categories (id, name, parent_id)
VALUES (207, 'Конструкции, препятствующие парковке', 204)
ON CONFLICT (id) DO UPDATE SET name='Конструкции, препятствующие парковке', parent_id=204;

INSERT INTO categories (id, name, parent_id)
VALUES (209, 'Лужи на проезжей части, тротуаре', 204)
ON CONFLICT (id) DO UPDATE SET name='Лужи на проезжей части, тротуаре', parent_id=204;

INSERT INTO categories (id, name, parent_id)
VALUES (211, 'Мусор на проезжей части, тротуаре, благоустроенных спусках к воде, подземных и надземных пешеходных переходах', 204)
ON CONFLICT (id) DO UPDATE SET name='Мусор на проезжей части, тротуаре, благоустроенных спусках к воде, подземных и надземных пешеходных переходах', parent_id=204;

INSERT INTO categories (id, name, parent_id)
VALUES (213, 'Мусор в непосредственной близости от здания станции метрополитена', 204)
ON CONFLICT (id) DO UPDATE SET name='Мусор в непосредственной близости от здания станции метрополитена', parent_id=204;

INSERT INTO categories (id, name, parent_id)
VALUES (215, 'Мусор на газонах вдоль проезжей части', 204)
ON CONFLICT (id) DO UPDATE SET name='Мусор на газонах вдоль проезжей части', parent_id=204;

INSERT INTO categories (id, name, parent_id)
VALUES (217, 'Неочищенные урны', 204)
ON CONFLICT (id) DO UPDATE SET name='Неочищенные урны', parent_id=204;

INSERT INTO categories (id, name, parent_id)
VALUES (219, 'Несанкционированные надписи / объявления на дорожных, газонных ограждениях и остановочных павильонах, не оборудованных рекламной конструкцией, вазонах, урнах и полусферах', 204)
ON CONFLICT (id) DO UPDATE SET name='Несанкционированные надписи / объявления на дорожных, газонных ограждениях и остановочных павильонах, не оборудованных рекламной конструкцией, вазонах, урнах и полусферах', parent_id=204;

INSERT INTO categories (id, name, parent_id)
VALUES (221, 'Несанкционированные надписи / объявления на технических средствах организации дорожного движения (светофоры, дорожные знаки)', 204)
ON CONFLICT (id) DO UPDATE SET name='Несанкционированные надписи / объявления на технических средствах организации дорожного движения (светофоры, дорожные знаки)', parent_id=204;

INSERT INTO categories (id, name, parent_id)
VALUES (223, 'Несанкционированные надписи /объявления на остановочных павильонах, совмещенных с рекламными конструкциями', 204)
ON CONFLICT (id) DO UPDATE SET name='Несанкционированные надписи /объявления на остановочных павильонах, совмещенных с рекламными конструкциями', parent_id=204;

INSERT INTO categories (id, name, parent_id)
VALUES (225, 'Неубранный снег на автомобильных дорогах и прилегающих к ним тротуарах, спусках к воде, подземных и надземных пешеходных переходах', 204)
ON CONFLICT (id) DO UPDATE SET name='Неубранный снег на автомобильных дорогах и прилегающих к ним тротуарах, спусках к воде, подземных и надземных пешеходных переходах', parent_id=204;

INSERT INTO categories (id, name, parent_id)
VALUES (227, 'Неудовлетворительное содержание газонов (нескошенная/не убранная трава)', 204)
ON CONFLICT (id) DO UPDATE SET name='Неудовлетворительное содержание газонов (нескошенная/не убранная трава)', parent_id=204;

INSERT INTO categories (id, name, parent_id)
VALUES (229, 'Повреждение дорожных и газонных ограждений, полусфер, вазонов, урн и остановочных павильонов, не оборудованных рекламной конструкцией', 204)
ON CONFLICT (id) DO UPDATE SET name='Повреждение дорожных и газонных ограждений, полусфер, вазонов, урн и остановочных павильонов, не оборудованных рекламной конструкцией', parent_id=204;

INSERT INTO categories (id, name, parent_id)
VALUES (231, 'Повреждения элементов дорожной разметки', 204)
ON CONFLICT (id) DO UPDATE SET name='Повреждения элементов дорожной разметки', parent_id=204;

INSERT INTO categories (id, name, parent_id)
VALUES (233, 'Повреждения дорожного покрытия проезжей части', 204)
ON CONFLICT (id) DO UPDATE SET name='Повреждения дорожного покрытия проезжей части', parent_id=204;

INSERT INTO categories (id, name, parent_id)
VALUES (235, 'Повреждения дорожного покрытия тротуара', 204)
ON CONFLICT (id) DO UPDATE SET name='Повреждения дорожного покрытия тротуара', parent_id=204;

INSERT INTO categories (id, name, parent_id)
VALUES (237, 'Повреждения дорожных знаков', 204)
ON CONFLICT (id) DO UPDATE SET name='Повреждения дорожных знаков', parent_id=204;

INSERT INTO categories (id, name, parent_id)
VALUES (239, 'Повреждения или неисправность светофоров', 204)
ON CONFLICT (id) DO UPDATE SET name='Повреждения или неисправность светофоров', parent_id=204;

INSERT INTO categories (id, name, parent_id)
VALUES (241, 'Повреждения информационного оборудования остановочных пунктов наземного городского пассажирского транспорта', 204)
ON CONFLICT (id) DO UPDATE SET name='Повреждения информационного оборудования остановочных пунктов наземного городского пассажирского транспорта', parent_id=204;

INSERT INTO categories (id, name, parent_id)
VALUES (243, 'Предложение по установке урны', 204)
ON CONFLICT (id) DO UPDATE SET name='Предложение по установке урны', parent_id=204;

INSERT INTO categories (id, name, parent_id)
VALUES (245, 'Угроза падения дерева на проезжую часть, тротуар', 204)
ON CONFLICT (id) DO UPDATE SET name='Угроза падения дерева на проезжую часть, тротуар', parent_id=204;

INSERT INTO categories (id, name, parent_id)
VALUES (247, 'Упавшее дерево', 204)
ON CONFLICT (id) DO UPDATE SET name='Упавшее дерево', parent_id=204;

INSERT INTO categories (id, name, parent_id)
VALUES (249, 'Санитарное состояние', 203)
ON CONFLICT (id) DO UPDATE SET name='Санитарное состояние', parent_id=203;

INSERT INTO categories (id, name, parent_id)
VALUES (250, 'Требуется дезинсекция (насекомые), дезинфекция в местах общего пользования (в т.ч. мусоропровода)', 249)
ON CONFLICT (id) DO UPDATE SET name='Требуется дезинсекция (насекомые), дезинфекция в местах общего пользования (в т.ч. мусоропровода)', parent_id=249;

INSERT INTO categories (id, name, parent_id)
VALUES (252, 'Требуется дератизация (крысы) в местах общего пользования', 249)
ON CONFLICT (id) DO UPDATE SET name='Требуется дератизация (крысы) в местах общего пользования', parent_id=249;

INSERT INTO categories (id, name, parent_id)
VALUES (254, 'Бюджетное учреждение', 0)
ON CONFLICT (id) DO UPDATE SET name='Бюджетное учреждение', parent_id=0;

INSERT INTO categories (id, name, parent_id)
VALUES (255, 'Образовательные учреждения', 254)
ON CONFLICT (id) DO UPDATE SET name='Образовательные учреждения', parent_id=254;

INSERT INTO categories (id, name, parent_id)
VALUES (256, 'Неудовлетворительное санитарное состояние помещений учреждения', 255)
ON CONFLICT (id) DO UPDATE SET name='Неудовлетворительное санитарное состояние помещений учреждения', parent_id=255;

INSERT INTO categories (id, name, parent_id)
VALUES (258, 'Отсутствие справочной информации на информационных стендах учреждений', 255)
ON CONFLICT (id) DO UPDATE SET name='Отсутствие справочной информации на информационных стендах учреждений', parent_id=255;

INSERT INTO categories (id, name, parent_id)
VALUES (260, 'Некомпитентность сотрдуников', 255)
ON CONFLICT (id) DO UPDATE SET name='Некомпитентность сотрдуников', parent_id=255;

INSERT INTO categories (id, name, parent_id)
VALUES (262, 'Отсутствие санитарных норм', 255)
ON CONFLICT (id) DO UPDATE SET name='Отсутствие санитарных норм', parent_id=255;

INSERT INTO categories (id, name, parent_id)
VALUES (264, 'Учреждения здравоохранения', 254)
ON CONFLICT (id) DO UPDATE SET name='Учреждения здравоохранения', parent_id=254;

INSERT INTO categories (id, name, parent_id)
VALUES (265, 'Неисправность/недоступность существующих объектов инфраструктуры для маломобильных граждан в учреждениях здравоохранения', 264)
ON CONFLICT (id) DO UPDATE SET name='Неисправность/недоступность существующих объектов инфраструктуры для маломобильных граждан в учреждениях здравоохранения', parent_id=264;

INSERT INTO categories (id, name, parent_id)
VALUES (267, 'Неудовлетворительное санитарное состояние в помещениях учреждения', 264)
ON CONFLICT (id) DO UPDATE SET name='Неудовлетворительное санитарное состояние в помещениях учреждения', parent_id=264;

INSERT INTO categories (id, name, parent_id)
VALUES (269, 'Ожидание приема (записи) в регистратуре амбулаторно-поликлинического учреждения более 30 минут', 264)
ON CONFLICT (id) DO UPDATE SET name='Ожидание приема (записи) в регистратуре амбулаторно-поликлинического учреждения более 30 минут', parent_id=264;

INSERT INTO categories (id, name, parent_id)
VALUES (271, 'Отсутствие бесплатных бахил в поликлинике для посещения кабинетов, где это предусмотрено санитарными правилами', 264)
ON CONFLICT (id) DO UPDATE SET name='Отсутствие бесплатных бахил в поликлинике для посещения кабинетов, где это предусмотрено санитарными правилами', parent_id=264;

INSERT INTO categories (id, name, parent_id)
VALUES (273, 'Отсутствие кабинета неотложной помощи в амбулаторно-поликлиническом учреждении', 264)
ON CONFLICT (id) DO UPDATE SET name='Отсутствие кабинета неотложной помощи в амбулаторно-поликлиническом учреждении', parent_id=264;

INSERT INTO categories (id, name, parent_id)
VALUES (275, 'Отсутствие сестринского поста в стационарном лечебном учреждении', 264)
ON CONFLICT (id) DO UPDATE SET name='Отсутствие сестринского поста в стационарном лечебном учреждении', parent_id=264;

INSERT INTO categories (id, name, parent_id)
VALUES (277, 'Отсутствие справочной информации на информационных стендах учреждений', 264)
ON CONFLICT (id) DO UPDATE SET name='Отсутствие справочной информации на информационных стендах учреждений', parent_id=264;

INSERT INTO categories (id, name, parent_id)
VALUES (279, 'Отсутствие технической возможности записи к врачу в интернете (через портал «Государственные и муниципальные услуги (функции) в Санкт-Петербурге» и портал записи к врачу Комитета по здравоохранению)', 264)
ON CONFLICT (id) DO UPDATE SET name='Отсутствие технической возможности записи к врачу в интернете (через портал «Государственные и муниципальные услуги (функции) в Санкт-Петербурге» и портал записи к врачу Комитета по здравоохранению)', parent_id=264;

INSERT INTO categories (id, name, parent_id)
VALUES (281, 'Отсутствие технической возможности записи к врачу по телефону «колл-центра»', 264)
ON CONFLICT (id) DO UPDATE SET name='Отсутствие технической возможности записи к врачу по телефону «колл-центра»', parent_id=264;

INSERT INTO categories (id, name, parent_id)
VALUES (283, 'Некомпитентность сотрудников', 264)
ON CONFLICT (id) DO UPDATE SET name='Некомпитентность сотрудников', parent_id=264;

INSERT INTO categories (id, name, parent_id)
VALUES (285, 'Учреждения культуры', 254)
ON CONFLICT (id) DO UPDATE SET name='Учреждения культуры', parent_id=254;

INSERT INTO categories (id, name, parent_id)
VALUES (286, 'Неудовлетворительное санитарное состояние в помещениях учреждений культуры', 285)
ON CONFLICT (id) DO UPDATE SET name='Неудовлетворительное санитарное состояние в помещениях учреждений культуры', parent_id=285;

INSERT INTO categories (id, name, parent_id)
VALUES (288, 'Отсутствие справочной информации на информационных стендах учреждений', 285)
ON CONFLICT (id) DO UPDATE SET name='Отсутствие справочной информации на информационных стендах учреждений', parent_id=285;

INSERT INTO categories (id, name, parent_id)
VALUES (290, 'Учреждения социальной защиты населения', 254)
ON CONFLICT (id) DO UPDATE SET name='Учреждения социальной защиты населения', parent_id=254;

INSERT INTO categories (id, name, parent_id)
VALUES (291, 'Неудовлетворительное санитарное состояние в помещениях учреждения', 290)
ON CONFLICT (id) DO UPDATE SET name='Неудовлетворительное санитарное состояние в помещениях учреждения', parent_id=290;

INSERT INTO categories (id, name, parent_id)
VALUES (293, 'Отсутствие справочной информации на информационных стендах учреждений', 290)
ON CONFLICT (id) DO UPDATE SET name='Отсутствие справочной информации на информационных стендах учреждений', parent_id=290;

INSERT INTO categories (id, name, parent_id)
VALUES (295, 'Учреждения сферы занятости', 254)
ON CONFLICT (id) DO UPDATE SET name='Учреждения сферы занятости', parent_id=254;

INSERT INTO categories (id, name, parent_id)
VALUES (296, 'Неудовлетворительное санитарное состояние в помещениях учреждения', 295)
ON CONFLICT (id) DO UPDATE SET name='Неудовлетворительное санитарное состояние в помещениях учреждения', parent_id=295;

INSERT INTO categories (id, name, parent_id)
VALUES (298, 'Отсутствие справочной информации на информационных стендах учреждений', 295)
ON CONFLICT (id) DO UPDATE SET name='Отсутствие справочной информации на информационных стендах учреждений', parent_id=295;

INSERT INTO categories (id, name, parent_id)
VALUES (300, 'Парк, сад, бульвар, сквер', 0)
ON CONFLICT (id) DO UPDATE SET name='Парк, сад, бульвар, сквер', parent_id=0;

INSERT INTO categories (id, name, parent_id)
VALUES (301, 'Благоустройство', 300)
ON CONFLICT (id) DO UPDATE SET name='Благоустройство', parent_id=300;

INSERT INTO categories (id, name, parent_id)
VALUES (302, 'Мусор на газонах ', 301)
ON CONFLICT (id) DO UPDATE SET name='Мусор на газонах ', parent_id=301;

INSERT INTO categories (id, name, parent_id)
VALUES (304, 'Ненадлежащее (неисправное) состояние уличной мебели и оборудования в садах, парках, скверах (в том числе на территориях детских и спортивных площадок, зон отдыха)', 301)
ON CONFLICT (id) DO UPDATE SET name='Ненадлежащее (неисправное) состояние уличной мебели и оборудования в садах, парках, скверах (в том числе на территориях детских и спортивных площадок, зон отдыха)', parent_id=301;

INSERT INTO categories (id, name, parent_id)
VALUES (306, 'Ненадлежащее состояние ограждений газонов', 301)
ON CONFLICT (id) DO UPDATE SET name='Ненадлежащее состояние ограждений газонов', parent_id=301;

INSERT INTO categories (id, name, parent_id)
VALUES (308, 'Неубранный снег на пешеходных дорожках в парках, садах, бульварах и скверах', 301)
ON CONFLICT (id) DO UPDATE SET name='Неубранный снег на пешеходных дорожках в парках, садах, бульварах и скверах', parent_id=301;

INSERT INTO categories (id, name, parent_id)
VALUES (310, 'Неудовлетворительное содержание газонов (нескошенная/не убранная трава)', 301)
ON CONFLICT (id) DO UPDATE SET name='Неудовлетворительное содержание газонов (нескошенная/не убранная трава)', parent_id=301;

INSERT INTO categories (id, name, parent_id)
VALUES (312, 'Неудовлетворительное содержание газонов (отсутствие травяного покрова)', 301)
ON CONFLICT (id) DO UPDATE SET name='Неудовлетворительное содержание газонов (отсутствие травяного покрова)', parent_id=301;

INSERT INTO categories (id, name, parent_id)
VALUES (314, 'Скопление мусора в садах, парках, скверах', 301)
ON CONFLICT (id) DO UPDATE SET name='Скопление мусора в садах, парках, скверах', parent_id=301;

INSERT INTO categories (id, name, parent_id)
VALUES (316, 'Угроза падения дерева или его части', 301)
ON CONFLICT (id) DO UPDATE SET name='Угроза падения дерева или его части', parent_id=301;

INSERT INTO categories (id, name, parent_id)
VALUES (318, 'Санитарное состояние', 300)
ON CONFLICT (id) DO UPDATE SET name='Санитарное состояние', parent_id=300;

INSERT INTO categories (id, name, parent_id)
VALUES (319, 'Требуется дезинсекция (насекомые), дезинфекция в местах общего пользования (в т.ч. мусоропровода)', 318)
ON CONFLICT (id) DO UPDATE SET name='Требуется дезинсекция (насекомые), дезинфекция в местах общего пользования (в т.ч. мусоропровода)', parent_id=318;

INSERT INTO categories (id, name, parent_id)
VALUES (321, 'Требуется дератизация (крысы) в местах общего пользования', 318)
ON CONFLICT (id) DO UPDATE SET name='Требуется дератизация (крысы) в местах общего пользования', parent_id=318;

INSERT INTO categories (id, name, parent_id)
VALUES (323, 'Общественный транспорт', 0)
ON CONFLICT (id) DO UPDATE SET name='Общественный транспорт', parent_id=0;

INSERT INTO categories (id, name, parent_id)
VALUES (324, 'Техническое состояние', 323)
ON CONFLICT (id) DO UPDATE SET name='Техническое состояние', parent_id=323;

INSERT INTO categories (id, name, parent_id)
VALUES (325, 'Неудовлетворительное техническое состояние транспортных средств', 324)
ON CONFLICT (id) DO UPDATE SET name='Неудовлетворительное техническое состояние транспортных средств', parent_id=324;

INSERT INTO categories (id, name, parent_id)
VALUES (327, 'Санитарное состояние', 323)
ON CONFLICT (id) DO UPDATE SET name='Санитарное состояние', parent_id=323;

INSERT INTO categories (id, name, parent_id)
VALUES (328, 'Требуется дезинсекция (насекомые), дезинфекция в местах общего пользования (в т.ч. мусоропровода)', 327)
ON CONFLICT (id) DO UPDATE SET name='Требуется дезинсекция (насекомые), дезинфекция в местах общего пользования (в т.ч. мусоропровода)', parent_id=327;

INSERT INTO categories (id, name, parent_id)
VALUES (330, 'Требуется дератизация (крысы) в местах общего пользования', 327)
ON CONFLICT (id) DO UPDATE SET name='Требуется дератизация (крысы) в местах общего пользования', parent_id=327;

INSERT INTO categories (id, name, parent_id)
VALUES (332, 'Мусор в общественном транспорте', 327)
ON CONFLICT (id) DO UPDATE SET name='Мусор в общественном транспорте', parent_id=327;

INSERT INTO categories (id, name, parent_id)
VALUES (334, 'Остановка общественного транспорта', 323)
ON CONFLICT (id) DO UPDATE SET name='Остановка общественного транспорта', parent_id=323;

INSERT INTO categories (id, name, parent_id)
VALUES (335, 'Повреждение остановочного павильона', 334)
ON CONFLICT (id) DO UPDATE SET name='Повреждение остановочного павильона', parent_id=334;

INSERT INTO categories (id, name, parent_id)
VALUES (337, 'Несанкционированные надписи / объявления', 334)
ON CONFLICT (id) DO UPDATE SET name='Несанкционированные надписи / объявления', parent_id=334;

INSERT INTO categories (id, name, parent_id)
VALUES (339, 'Другое', 0)
ON CONFLICT (id) DO UPDATE SET name='Другое', parent_id=0;

INSERT INTO categories (id, name, parent_id)
VALUES (340, 'Другое', 339)
ON CONFLICT (id) DO UPDATE SET name='Другое', parent_id=339;

INSERT INTO categories (id, name, parent_id)
VALUES (341, 'Другое', 340)
ON CONFLICT (id) DO UPDATE SET name='Другое', parent_id=340;

