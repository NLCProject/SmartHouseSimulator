package test.filterServiceTest

import org.household.Application
import org.household.entities.powerCharger.PowerChargerEntity
import org.household.entities.powerCharger.PowerChargerFilterService
import org.household.entities.powerCharger.PowerChargerRepository
import org.household.test.TestHelperService
import org.isc.utils.models.CurrentUser
import org.isc.utils.models.filter.FilterOptions
import org.isc.utils.models.filter.FilterParameters
import org.isc.utils.tests.FilterServiceTest
import org.junit.jupiter.api.Assertions.assertTrue
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import javax.annotation.PostConstruct

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = [Application::class])
class PowerChargerFilterServiceTest : FilterServiceTest<PowerChargerEntity>() {

    @Autowired
    private lateinit var repositoryService: PowerChargerRepository

    @Autowired
    private lateinit var filterService: PowerChargerFilterService

    @Autowired
    private lateinit var testHelperService: TestHelperService

    @PostConstruct
    fun init() {
        super.init(
            filterService = filterService,
            repositoryService = repositoryService,
            instancesToCreate = 2,
            additionalFeatures = mutableListOf(),
            additionalRoles = mutableListOf()
        )
    }

    override fun createFilters(filters: FilterOptions) {
        assertTrue(filters.isEmpty())
    }

    override fun findAllHints(entities: List<PowerChargerEntity>) {
        val models = filterService.findAllHints(filter = FilterParameters(), currentUser = currentUser)
        assertTrue(models.toList().isEmpty())
    }

    override fun createEntity(currentUser: CurrentUser): PowerChargerEntity =
        testHelperService.createPowerCharger(currentUser = currentUser)
}