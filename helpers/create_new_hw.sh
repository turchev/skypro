#!/usr/bin/env bash
set -euo pipefail

SCRIPT_DIR=$(cd "$(dirname "${BASH_SOURCE[0]}")" &>/dev/null && pwd -P)
HW_OLD_NAME='SKY_PRO_HW'
RENAME_CMD=`which rename`
POM_FILE='pom.xml'
ASSEMBLY_ALL_SCRIPT='assembly_all.sh'

echo "Script directory: $SCRIPT_DIR"

read -p "Enter the name of the new homework: " HW_NEW_NAME
if [[ $HW_NEW_NAME == '' ]]; then
    echo "The name of the new homework is required" >&2
    sleep 2
    exit 1
fi

# 1
git checkout -b $HW_NEW_NAME

# 2
cp -a $SCRIPT_DIR/template/$HW_OLD_NAME ../$HW_NEW_NAME
cd ../../skypro

# 3
echo "The old \"$HW_OLD_NAME\" name will be replaced by the new \"$HW_NEW_NAME\" in the following files:"
grep -rl $HW_OLD_NAME $HW_NEW_NAME
grep -rl $HW_OLD_NAME $HW_NEW_NAME | xargs -r sed -i "s/$HW_OLD_NAME/$HW_NEW_NAME/g"

# 4
find $HW_NEW_NAME -depth -name "*$HW_OLD_NAME*" | xargs -r $RENAME_CMD "s/$HW_OLD_NAME/$HW_NEW_NAME/"

# 5
sed -i "s/<modules>/<modules>\n\t\t<module>$HW_NEW_NAME<\/module>/" $POM_FILE

# 6 
MODULE_START_SCRIPT="$SCRIPT_DIR/start_$HW_NEW_NAME.sh"
echo "#!/bin/bash
cd ../../skypro
mvn -pl $HW_NEW_NAME clean
mvn -pl $HW_NEW_NAME compile exec:exec" > "$MODULE_START_SCRIPT"

chmod a+x $MODULE_START_SCRIPT

# 7
sed -i "s/mvn clean/mvn clean\nmvn -pl $HW_NEW_NAME compile package/" "$SCRIPT_DIR/$ASSEMBLY_ALL_SCRIPT"
 
# 8
mvn -pl $HW_NEW_NAME compile exec:exec













