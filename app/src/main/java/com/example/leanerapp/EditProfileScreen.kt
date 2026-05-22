package com.example.leanerapp

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.leanerapp.viewmodel.ProfileViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditProfileScreen(
    viewModel: ProfileViewModel = hiltViewModel(),
    onBack: () -> Unit
) {
    val currentName by viewModel.name.collectAsStateWithLifecycle()
    val currentRole by viewModel.role.collectAsStateWithLifecycle()
    val currentBio by viewModel.bio.collectAsStateWithLifecycle()

    // Local form state
    var name by remember(currentName) { mutableStateOf(currentName) }
    var role by remember(currentRole) { mutableStateOf(currentRole) }
    var bio by remember(currentBio) { mutableStateOf(currentBio) }

    // Validation errors
    var nameError by remember { mutableStateOf("") }
    var roleError by remember { mutableStateOf("") }
    var bioError by remember { mutableStateOf("") }

    // Success dialog
    var showSuccess by remember { mutableStateOf(false) }

    fun validate(): Boolean {
        var isValid = true
        nameError = if (name.isBlank()) {
            isValid = false
            "Name cannot be empty"
        } else if (name.length < 2) {
            isValid = false
            "Name must be at least 2 characters"
        } else ""

        roleError = if (role.isBlank()) {
            isValid = false
            "Role cannot be empty"
        } else if (role.length < 2) {
            isValid = false
            "Role must be at least 2 characters"
        } else ""

        bioError = if (bio.length > 150) {
            isValid = false
            "Bio cannot exceed 150 characters"
        } else ""

        return isValid
    }

    if (showSuccess) {
        AlertDialog(
            onDismissRequest = { showSuccess = false },
            title = { Text("Success!") },
            text = { Text("Your profile has been updated successfully.") },
            confirmButton = {
                TextButton(onClick = {
                    showSuccess = false
                    onBack()
                }) {
                    Text("OK")
                }
            }
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Edit Profile",
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Go back"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            
            ElevatedCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.elevatedCardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    
                    Text(
                        text = "Profile Information",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )

                    // Name field
                    EditField(
                        value = name,
                        onValueChange = {
                            name = it
                            nameError = ""
                        },
                        label = "Full Name",
                        icon = Icons.Default.Person,
                        error = nameError,
                        contentDescription = "Full name input field"
                    )

                    // Role field
                    EditField(
                        value = role,
                        onValueChange = {
                            role = it
                            roleError = ""
                        },
                        label = "Role",
                        icon = Icons.Default.Work,
                        error = roleError,
                        contentDescription = "Role input field"
                    )

                    // Bio field
                    EditField(
                        value = bio,
                        onValueChange = {
                            bio = it
                            bioError = ""
                        },
                        label = "Bio",
                        icon = Icons.Default.Info,
                        error = bioError,
                        isSingleLine = false,
                        maxLines = 4,
                        contentDescription = "Bio input field",
                        supportingText = "${bio.length}/150 characters"
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Action Buttons
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Button(
                    onClick = {
                        if (validate()) {
                            viewModel.saveProfile(name, role, bio)
                            showSuccess = true
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Text(
                        text = "Save Profile",
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Bold
                    )
                }

                OutlinedButton(
                    onClick = onBack,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Text(
                        text = "Cancel",
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
fun EditField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    icon: ImageVector,
    error: String,
    contentDescription: String,
    isSingleLine: Boolean = true,
    maxLines: Int = 1,
    supportingText: String? = null
) {
    Column {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            label = { Text(label) },
            leadingIcon = {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = if (error.isNotEmpty()) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.primary
                )
            },
            isError = error.isNotEmpty(),
            modifier = Modifier
                .fillMaxWidth()
                .semantics {
                    this.contentDescription = contentDescription
                },
            singleLine = isSingleLine,
            maxLines = maxLines,
            shape = RoundedCornerShape(12.dp)
        )
        
        AnimatedVisibility(
            visible = error.isNotEmpty(),
            enter = fadeIn() + expandVertically(),
            exit = fadeOut() + shrinkVertically()
        ) {
            Text(
                text = error,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(start = 16.dp, top = 4.dp)
            )
        }
        
        if (error.isEmpty() && supportingText != null) {
            Text(
                text = supportingText,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(start = 16.dp, top = 4.dp)
            )
        }
    }
}
