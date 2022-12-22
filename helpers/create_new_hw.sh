#!/usr/bin/env bash
set -euo pipefail

SCRIPT_DIR_PATH=$(cd "$(dirname "${BASH_SOURCE[0]}")" &>/dev/null && pwd -P)
SCRIPT_DIR_NAME=$(basename "$SCRIPT_DIR_PATH")
HW_OLD_NAME='SKY_PRO_HW'
RENAME_CMD=$(which rename)
POM_FILE='pom.xml'
ASSEMBLY_ALL_SCRIPT='assembly_all.sh'
DEFAULT_BRANCH='master'

echo "Script directory: $SCRIPT_DIR_PATH"

# shellcheck disable=SC2162
read -p "Enter the name of the new homework: " HW_NEW_NAME
if [[ $HW_NEW_NAME == '' ]]; then
  echo "The name of the new homework is required" >&2
  sleep 2
  exit 1
fi

# 1
git checkout -b "$HW_NEW_NAME" $DEFAULT_BRANCH

# 2
cp -a "$SCRIPT_DIR_PATH"/template/$HW_OLD_NAME ../"$HW_NEW_NAME"
cd ../../skypro

# 3
echo "The old \"$HW_OLD_NAME\" name will be replaced by the new \"$HW_NEW_NAME\" in the following files:"
grep -rl $HW_OLD_NAME "$HW_NEW_NAME"
grep -rl $HW_OLD_NAME "$HW_NEW_NAME" | xargs -r sed -i "s/$HW_OLD_NAME/$HW_NEW_NAME/g"

# 4
# shellcheck disable=SC2038
find "$HW_NEW_NAME" -depth -name "*$HW_OLD_NAME*" | xargs -r "$RENAME_CMD" "s/$HW_OLD_NAME/$HW_NEW_NAME/"

# 5
sed -i "s/<modules>/<modules>\n        <module>$HW_NEW_NAME<\/module>/" $POM_FILE

# 6
MODULE_START_SCRIPT_NAME="start_$HW_NEW_NAME.sh"
MODULE_START_SCRIPT_PATH="$SCRIPT_DIR_PATH/$MODULE_START_SCRIPT_NAME"
echo "#!/bin/bash
cd ../../skypro
mvn -pl $HW_NEW_NAME clean
mvn -pl $HW_NEW_NAME compile exec:exec" >"$MODULE_START_SCRIPT_PATH"

chmod a+x "$MODULE_START_SCRIPT_PATH"

# 7
sed -i "s/mvn clean/mvn clean\nmvn -pl $HW_NEW_NAME compile package/" "$SCRIPT_DIR_PATH/$ASSEMBLY_ALL_SCRIPT"

# 8
mvn -pl "$HW_NEW_NAME" compile exec:exec

# 9
git add "$HW_NEW_NAME" $POM_FILE "$SCRIPT_DIR_NAME/$ASSEMBLY_ALL_SCRIPT" "$SCRIPT_DIR_NAME/$MODULE_START_SCRIPT_NAME"
git commit -m "Initial $HW_NEW_NAME"
#git push origin "$HW_NEW_NAME"
# shellcheck disable=SC2162
read -p "Name of the working branch of the module (optional): " HW_NEW_BRANCH
if [[ $HW_NEW_BRANCH == '' ]]; then
  echo "Work branch not created"
  sleep 2
  exit 0
fi
git checkout -b "$HW_NEW_BRANCH" "$HW_NEW_NAME"
echo "Work branch name $HW_NEW_BRANCH"
