import pandas as pd
from sklearn.model_selection import train_test_split
from sklearn.tree import DecisionTreeClassifier
from sklearn.metrics import accuracy_score

# Load the dataset from CSV file
df = pd.read_csv("../Datasets/vrexpo_testcases_populated.csv")

# Manually encode categorical variables and handle "N/A" values
mapping_dict = {}
for column in df.columns:
    if df[column].dtype == 'object':
        # Map each unique value to a specific number
        mapping = {val: i for i, val in enumerate(df[column].unique())}
        # Replace "N/A" with a unique value (e.g., -1)
        mapping['N/A'] = -1
        df[column] = df[column].map(mapping)
        # Store the mapping for each column
        mapping_dict[column] = {v: k for k, v in mapping.items()}

# Split data into features and target variable
X = df.drop(columns=['TreatmentPlans'])
y = df['TreatmentPlans']

# Split data into training and testing sets
X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.2, random_state=32)

# Train the decision tree classifier
model = DecisionTreeClassifier(random_state=32)
model.fit(X_train, y_train)

# Predict treatment plans for the test set
y_pred = model.predict(X_test)

# Calculate accuracy of the model
accuracy = accuracy_score(y_test, y_pred)
print("Decision Tree Model Accuracy:", accuracy)

# For example, let's assume new_df contains the new test set data
new_df = pd.DataFrame({
    'PatientCondition': ['Panic Disorder'],
    'PatientPhobia': ['N/A'],
    'PatientPTSD': ['N/A'],
    'TherapSpecialization': ['General'],
})

# Perform manual encoding and handle "N/A" values for new_df
for column in new_df.columns:
    if new_df[column].dtype == 'object':
        new_df[column] = new_df[column].map(mapping).fillna(-1)

# Predict treatment plans for the new test set
new_predictions = model.predict(new_df)

if new_predictions == 0:
    treatment_plan = mapping_dict['TreatmentPlans'][0]
    print("Predicted Treatment Plan: ", treatment_plan)
if new_predictions == 1:
    treatment_plan = mapping_dict['TreatmentPlans'][1]
    print("Predicted Treatment Plan: ", treatment_plan)
if new_predictions == 2:
    treatment_plan = mapping_dict['TreatmentPlans'][2]
    print("Predicted Treatment Plan: ", treatment_plan)
if new_predictions == 3:
    treatment_plan = mapping_dict['TreatmentPlans'][3]
    print("Predicted Treatment Plan: ", treatment_plan)
if new_predictions == 4:
    treatment_plan = mapping_dict['TreatmentPlans'][4]
    print("Predicted Treatment Plan: ", treatment_plan)
if new_predictions == 5:
    treatment_plan = mapping_dict['TreatmentPlans'][5]
    print("Predicted Treatment Plan: ", treatment_plan)
if new_predictions == 6:
    treatment_plan = mapping_dict['TreatmentPlans'][6]
    print("Predicted Treatment Plan: ", treatment_plan)
if new_predictions == 7:
    treatment_plan = mapping_dict['TreatmentPlans'][7]
    print("Predicted Treatment Plan: ", treatment_plan)
