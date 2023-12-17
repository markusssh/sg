package dev.sg.database_initialization_dev;

import dev.sg.DTOs.user.RegistrationModeratorDTO;
import dev.sg.DTOs.user.RegistrationUserDTO;
import dev.sg.entities.CategoryEntity;
import dev.sg.entities.LinkEntity;
import dev.sg.entities.ReportEntity;
import dev.sg.entities.RoleEntity;
import dev.sg.entities.UserEntity;
import dev.sg.enums.Gender;
import dev.sg.enums.Status;
import dev.sg.repositories.CategoryRepo;
import dev.sg.repositories.ReportRepo;
import dev.sg.repositories.RoleRepo;
import dev.sg.repositories.UserRepo;
import dev.sg.services.AuthService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
@Slf4j
@Profile("dev")
@RequiredArgsConstructor
public class DataInitializer {

    private final AuthService authService;
    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
    private final ReportRepo reportRepo;
    private final CategoryRepo categoryRepo;
    private static final Logger logger = LoggerFactory.getLogger(DataInitializer.class);
    private List<Integer> possibleSubcategoriesIDs;

    @Transactional
    public void initialize() {
        if (categoryRepo.count() == 0) initializeCategories();
        this.possibleSubcategoriesIDs = getPossibleSubcategoriesIDs();
        initializeRoles();
        generateUsers();
        if (reportRepo.count() == 0) generateReports();
    }

    private void initializeCategories() {
        String fileName = "categories_data.txt";
        ClassLoader classLoader = DataInitializer.class.getClassLoader();
        try (InputStream inputStream = classLoader.getResourceAsStream(fileName)) {
            assert inputStream != null;
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    CategoryEntity category = parseCategoryLine(line);
                    if (category != null) categoryRepo.save(category);
                    else return;
                }
            }
        } catch (IOException e) {
            logger.error("An error occurred while reading the file '{}'", fileName, e);
        }
    }

    private CategoryEntity parseCategoryLine(String line) {
        Pattern pattern = Pattern.compile("(\\d+),\\s*'([^']+)',\\s*(\\d+)");
        Matcher matcher = pattern.matcher(line);
        if (matcher.find()) {
            int id = Integer.parseInt(matcher.group(1));
            String name = matcher.group(2);
            int parentId = Integer.parseInt(matcher.group(3));
            return new CategoryEntity(id, name, parentId);
        } else {
            return null;
        }
    }

    private void initializeRoles() {
        roleRepo.save(RoleEntity.builder().id(1).name("ROLE_USER").build());
        roleRepo.save(RoleEntity.builder().id(2).name("ROLE_MODERATOR").build());
    }

    private List<Integer> getPossibleSubcategoriesIDs() {
        List<CategoryEntity> categories = categoryRepo.findAll();
        List<Integer> response = new ArrayList<>();
        for (CategoryEntity categoryEntity : categories) {
            if (getSubLevel(categoryEntity) == 3) {
                response.add(categoryEntity.getId());
            }
        }
        return response;
    }

    private int getSubLevel(CategoryEntity categoryEntity) {
        if (categoryEntity.getParentId() == 0) {
            return 1;
        } else {
            CategoryEntity parentEntity = categoryRepo.findById(categoryEntity.getParentId()).orElseThrow();
            return switch (getSubLevel(parentEntity)) {
                case 1 -> 2;
                case 2 -> 3;
                default -> throw new IllegalStateException("Unexpected value");
            };
        }
    }

    private void generateUsers() {
        if (userRepo.findByUsername("9323365668").isEmpty()) {
            authService.createNewUser(
                    RegistrationUserDTO.builder()
                            .username("9323365668")
                            .password("password")
                            .name("Юзер")
                            .surname("Юзеров")
                            .patronymic("Юзерович")
                            .birthdate("2002-03-01")
                            .email("user@email.com")
                            .gender(Gender.M)
                            .build()
            );
        }
        if (userRepo.findByUsername("9999999999").isEmpty()) {
            authService.createNewUser(
                    RegistrationUserDTO.builder()
                            .username("9999999999")
                            .password("password")
                            .name("Юзерина")
                            .surname("Юзерова")
                            .patronymic("Юзеровна")
                            .birthdate("2024-01-01")
                            .email("user1@email.com")
                            .gender(Gender.F)
                            .build()
            );
        }
        if (userRepo.findByUsername("8888888888").isEmpty()) {
            authService.createNewUser(
                    RegistrationUserDTO.builder()
                            .username("8888888888")
                            .password("password")
                            .name("ААА")
                            .surname("АААов")
                            .patronymic("АААвич")
                            .birthdate("2077-04-13")
                            .email("user2@email.com")
                            .gender(Gender.M)
                            .build()
            );
        }

        if (userRepo.findByUsername("moder").isEmpty()) {
            authService.createNewModer(
                    RegistrationModeratorDTO.builder()
                            .username("moder")
                            .password("password")
                            .name("Модер")
                            .surname("Модеров")
                            .patronymic("Модерович")
                            .phone(9323365668L)
                            .gender(Gender.M)
                            .build()
            );
        }
    }

    private void generateReports() {
        int totalReports = 10000;
        Map<Integer, String> categoryNames = loadCategoryNames();

        List<ReportEntity> reportsToSave = IntStream.range(0, totalReports)
                .mapToObj(i -> {
                    ReportEntity reportEntity = new ReportEntity();
                    reportEntity.setLinks(randomizeLinks());
                    reportEntity.setCategoryId(randomizeCategoryId());
                    reportEntity.setAddress("*address");
                    reportEntity.setGeotag(randomizeGeotag());
                    reportEntity.setUser(randomizeUser());
                    reportEntity.setBody(randomizeBody(categoryNames.get(reportEntity.getCategoryId())));
                    reportEntity.setIsStatusChanged(randomizeBool());
                    reportEntity.setCreatedAt(randomizeCreatedAt());
                    reportEntity.setStatus(randomizeStatus());
                    return reportEntity;
                }).collect(Collectors.toList());

        reportRepo.saveAll(reportsToSave);
    }

    private List<LinkEntity> randomizeLinks() {
        Random random = new Random();
        String[] links = new String[]{
                "https://api.psychologos.ru/storage/image/548226ccc5a145dfd3ddcc263fa3a04e.jpg",
                "https://gsrsystem.ru/wp-content/uploads/2020/01/IMG_6958.jpg",
                "https://udoba.org/sites/default/files/h5p/content/6879/images/image-6033a7f2bd538.jpg",
                "https://a.d-cd.net/3a51d96s-1920.jpg",
                "https://png.pngtree.com/png-vector/20210312/ourlarge/pngtree-a-person-who-is-oppressed-by-the-problem-png-image_3050020.jpg",
                "https://t4.ftcdn.net/jpg/02/79/13/27/360_F_279132718_FwcEPzRC62Eay4creqr8eG0Q4Bkc9PuF.jpg",
                "y_50/v1481647111/i5kq2sxt7lregfvxo6al.jpg",
                "https://i08.fotocdn.net/s122/595d19f7c5f12696/public_pin_l/2792742172.jpg",
                "https://a.d-cd.net/818e64ds-1920.jpg",
                "https://a.d-cd.net/2WAAAgDUieA-960.jpg",
                "https://moyaokruga.ru/img/image_big/73a1cb3c-7f41-4c54-9bd9-28ea8113c981.jpg",
                "https://i.pinimg.com/originals/73/ed/d9/73edd99a9adc697adbfbd85d90638d29.jpg"
        };

        List<LinkEntity> response = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            response.add(LinkEntity.builder().link(links[random.nextInt(links.length)]).build());
        }
        return response;
    }

    private String randomizeGeotag() {
        Random random = new Random();
        double latitude = -90 + (90 - (-90)) * random.nextDouble();
        double longitude = -180 + (180 - (-180)) * random.nextDouble();
        return String.format("%.6f %.6f", latitude, longitude);
    }

    private Map<Integer, String> loadCategoryNames() {
        Map<Integer, String> categoryNames = new HashMap<>();
        categoryRepo.findAll().forEach(category -> categoryNames.put(category.getId(), category.getName()));
        return categoryNames;
    }

    private Integer randomizeCategoryId() {
        Random random = new Random();
        return possibleSubcategoriesIDs.get(random.nextInt(possibleSubcategoriesIDs.size()));
    }

    private Status randomizeStatus() {
        Random random = new Random();
        int randStatus = random.nextInt(4);
        return switch (randStatus) {
            case 0 -> Status.NEW;
            case 1 -> Status.PENDING;
            case 2 -> Status.DENIED;
            case 3 -> Status.APPROVED;
            default -> throw new IllegalStateException("Unexpected value");
        };
    }

    private LocalDateTime randomizeCreatedAt() {
        Random random = new Random();
        int startingYear = 2022;
        int year = startingYear + random.nextInt(LocalDateTime.now().getYear() - startingYear + 1);
        int month = 1 + random.nextInt(12);
        int day = 1 + random.nextInt(LocalDateTime.of(year, month, 1, 0, 0).toLocalDate().lengthOfMonth());
        int hour = random.nextInt(24);
        int minute = random.nextInt(60);
        int second = random.nextInt(60);
        return LocalDateTime.of(year, month, day, hour, minute, second);
    }

    private Boolean randomizeBool() {
        Random random = new Random();
        return random.nextBoolean();
    }

    private String randomizeBody(String categoryName) {
        Random random = new Random();
        String[] greeting = new String[]{
                "Здравствуйте! ",
                "Приветствую. ",
                "Добрый день! ",
                "Доброе утро! ",
                "Добрый вечер! ",
                "Доброго времени суток! ",
                "Приветствую вас. ",
        };
        String[] problem = new String[]{
                "У меня неприятная проблема - ",
                "Мне нужна помощь с одной неприятностью: ",
                "Присылаю вам жалобу по поводу моей проблемы - ",
                "У меня беда! ",
                "У меня возникла неприятность: ",
                "Сложилась проблема, требующая вашего внимания - ",
                "Мне не удается справиться с ситуацией, вот что произошло: ",
                "Нужна ваша помощь в решении проблемы - "
        };
        String[] ending = new String[]{
                ". Очень прошу, разберитесь как можно скорее!",
                ". Заранее спасибо!",
                ". Требую, чтобы кто-то незамедлительно занялся решением данного вопроса.",
                ". Надеюсь на вашу оперативную помощь!",
                ". Буду признателен за скорое разрешение проблемы.",
                ". Очень важно решить этот вопрос как можно быстрее.",
                ". Прошу рассмотреть этот вопрос срочно и принять необходимые меры.",
                ". Пожалуйста, уделите внимание данной ситуации и решите её."
        };

        return greeting[random.nextInt(greeting.length)] +
                problem[random.nextInt(problem.length)] +
                categoryName +
                ending[random.nextInt(ending.length)];
    }

    private UserEntity randomizeUser() {
        Random random = new Random();
        int randUser = random.nextInt(3);
        return switch (randUser) {
            case 0 -> userRepo.findByUsername("9323365668").get();
            case 1 -> userRepo.findByUsername("9999999999").get();
            case 2 -> userRepo.findByUsername("8888888888").get();
            default -> throw new IllegalStateException("Unexpected value: " + randUser);
        };
    }
}
