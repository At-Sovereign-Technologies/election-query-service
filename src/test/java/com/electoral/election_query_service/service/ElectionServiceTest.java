package com.electoral.election_query_service.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ElectionServiceTest {

    /*@Mock
    private ElectionRepository repository;

    @Mock
    private RedisCacheAdapter cache;

    @Mock
    private ElectionMapper mapper;

    @InjectMocks
    private ElectionService service;

    private Election election;
    private ElectionResponse response;

    @BeforeEach
    void setup() {
        election = new Election();
        election.setId(1L);
        election.setName("Elección Test");

        response = new ElectionResponse();
        response.setId(1L);
        response.setName("Elección Test");
    }

    // GET ALL - CACHE HIT
    @Test
    void shouldReturnFromCacheWhenGetAll() {
        when(cache.get("elections:all")).thenReturn((Object) List.of(response));

        List<ElectionResponse> result = service.getAll();

        assertEquals(1, result.size());
        verify(repository, never()).findAll();
    }

    // GET ALL - CACHE MISS
    @Test
    void shouldQueryDbAndStoreCacheWhenGetAll() {
        when(cache.get("elections:all")).thenReturn(null);
        when(repository.findAll()).thenReturn(List.of(election));
        when(mapper.toResponse(election)).thenReturn(response);

        List<ElectionResponse> result = service.getAll();

        assertEquals(1, result.size());

        verify(repository).findAll();
        verify(cache).set(eq("elections:all"), any());
    }

    // GET BY ID - CACHE HIT
    @Test
    void shouldReturnFromCacheWhenGetById() {
        when(cache.get("election:1")).thenReturn((Object) response);

        ElectionResponse result = service.getById(1L);

        assertNotNull(result);
        verify(repository, never()).findById(any());
    }

    // GET BY ID - CACHE MISS
    @Test
    void shouldQueryDbAndStoreCacheWhenGetById() {
        when(cache.get("election:1")).thenReturn(null);
        when(repository.findById(1L)).thenReturn(Optional.of(election));
        when(mapper.toResponse(election)).thenReturn(response);

        ElectionResponse result = service.getById(1L);

        assertNotNull(result);

        verify(repository).findById(1L);
        verify(cache).set("election:1", response);
    }

    // NOT FOUND
    @Test
    void shouldThrowExceptionWhenElectionNotFound() {
        when(cache.get("election:1")).thenReturn(null);
        when(repository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            service.getById(1L);
        });
    }*/
}