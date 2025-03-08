package com.plcoding.bookpedia.app

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.plcoding.bookpedia.book.presentation.SelectedBookViewModel
import com.plcoding.bookpedia.book.presentation.book_detail.BookDetailScreenRoot
import com.plcoding.bookpedia.book.presentation.book_detail.BookDetailViewModel
import com.plcoding.bookpedia.book.presentation.book_detail.IBookDetailAction
import com.plcoding.bookpedia.book.presentation.book_list.BookListScreenRoot
import com.plcoding.bookpedia.book.presentation.book_list.BookListViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
@Preview
fun App() {
    MaterialTheme {
        val navController = rememberNavController()
        NavHost(
            navController = navController,
            startDestination = IRoute.BookGraph
        ) {
            navigation<IRoute.BookGraph>(
                startDestination = IRoute.BookList
            ) {
                composable<IRoute.BookList>(
                    exitTransition = { slideOutHorizontally() },
                    popEnterTransition = { slideInHorizontally() }
                ) {
                    val viewModel = koinViewModel<BookListViewModel>()
                    val selectedBookViewModel =
                        it.sharedKoinViewModel<SelectedBookViewModel>(navController)

                    // If we return on this screen, we want to reset the selected book
                    LaunchedEffect(true) {
                        selectedBookViewModel.onSelectedBook(null)
                    }

                    BookListScreenRoot(
                        viewModel = viewModel,
                        onBookClick = { book ->
                            // Update the selected book of the selected book view model
                            selectedBookViewModel.onSelectedBook(book)

                            navController.navigate(
                                IRoute.BookDetail(book.id)
                            )
                        }
                    )
                }
                composable<IRoute.BookDetail>(
                    enterTransition = {
                        slideInHorizontally { initialOffsetX -> initialOffsetX }
                    },
                    exitTransition = {
                        slideOutHorizontally { initialOffsetX -> initialOffsetX }
                    }
                ) { entry ->
//                    var args = entry.toRoute<IRoute.BookDetail>()

                    val selectedBookViewModel =
                        entry.sharedKoinViewModel<SelectedBookViewModel>(navController)
                    val selectedBook by selectedBookViewModel.selectedBook.collectAsStateWithLifecycle()
                    val bookDetailViewModel = koinViewModel<BookDetailViewModel>()

                    LaunchedEffect(selectedBook) {
                        selectedBook?.let {
                            bookDetailViewModel.onAction(IBookDetailAction.OnSelectedBookChange(it))
                        }
                    }

                    BookDetailScreenRoot(
                        viewModel = bookDetailViewModel,
                        onBackClick = {
                            // Return to the previous screen
                            navController.navigateUp()
                        }
                    )
                }

            }
        }

    }
}

/**
 * Return the instance of the ViewModel from the parent NavGraph.
 * If no instance is found, a new one will be created.
 *
 * @param T
 * @param navController
 * @return
 */
@Composable
private inline fun <reified T : ViewModel> NavBackStackEntry.sharedKoinViewModel(
    navController: NavController
): T {
    val navGraphRoute = destination.parent?.route ?: return koinViewModel<T>()
    val parentEntry = remember(this) {
        navController.getBackStackEntry(navGraphRoute)
    }
    return koinViewModel(
        viewModelStoreOwner = parentEntry
    )
}
